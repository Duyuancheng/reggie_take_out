package com.dyc.reggie_take_out1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyc.reggie_take_out1.common.CustomException;
import com.dyc.reggie_take_out1.entity.Category;
import com.dyc.reggie_take_out1.entity.Dish;
import com.dyc.reggie_take_out1.entity.Setmeal;
import com.dyc.reggie_take_out1.mapper.CategoryMapper;
import com.dyc.reggie_take_out1.service.CategoryService;
import com.dyc.reggie_take_out1.service.DishService;
import com.dyc.reggie_take_out1.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类，删除之前需要进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        //条件构造器
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper=new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count = (int) dishService.count(dishLambdaQueryWrapper);
        //查询当前分类是否关联了菜品，如果关联，抛出业务异常
        if(count>0){
            //已经关联菜品，抛出业务异常
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }

        //查询当前分类是否关联了套餐，如果关联，抛出业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper=new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count1 = (int) setmealService.count();
        if (count1>0){
            //已经关联套餐，抛出业务异常
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }
        //正常删除分类
        super.removeById(id);
    }
}
