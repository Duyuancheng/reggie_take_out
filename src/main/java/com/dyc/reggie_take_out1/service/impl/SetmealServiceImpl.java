package com.dyc.reggie_take_out1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyc.reggie_take_out1.common.CustomException;
import com.dyc.reggie_take_out1.dto.SetmealDto;
import com.dyc.reggie_take_out1.entity.Setmeal;
import com.dyc.reggie_take_out1.entity.SetmealDish;
import com.dyc.reggie_take_out1.mapper.SetmealMapper;
import com.dyc.reggie_take_out1.service.SetmealDishService;
import com.dyc.reggie_take_out1.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;
    /**
     * 新增套餐，同时保存套餐和菜品的关联关系
     * @param setmealDto
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐的基本信息操作setmeal执行insert操作
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        //保存套餐和菜品的关联信息，操作setmeal_dish，执行insert操作
        //saveBatch()批量保存
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * 删除套餐和菜品关联数据
     * @param ids
     */
    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
        //select count(*) from setmeal where id in (1,2,3) and status = 1;
        //查询套餐状态确定是否能删除
        LambdaQueryWrapper<Setmeal> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);
        int count = (int) this.count(queryWrapper);
        //如果不能删除抛出业务异常
        if (count>0){
            throw new CustomException("套餐正在售卖中，不能删除");
        }

        //如果可以，先删套餐表中数据----setmeal
        this.removeByIds(ids);
        //删除关系表中数据---setmeal_dish
        //delete from setmeal_dish where setmeal_id in ()
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(lambdaQueryWrapper);
    }
}
