package com.dyc.reggie_take_out1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dyc.reggie_take_out1.dto.SetmealDto;
import com.dyc.reggie_take_out1.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增套餐，同时保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐和菜品关联数据
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
