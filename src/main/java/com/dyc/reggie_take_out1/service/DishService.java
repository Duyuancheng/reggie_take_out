package com.dyc.reggie_take_out1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dyc.reggie_take_out1.dto.DishDto;
import com.dyc.reggie_take_out1.entity.Dish;

public interface DishService extends IService<Dish> {

    //新增菜品，同时插入菜品对应口味数据，需要操作两张表dish、dishflavor
    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和口味信息
    public DishDto getByIdWithFlavor(Long id);

    //更新菜品信息,同时更新口味信息
    public void updateWithFlavor(DishDto dishDto);
}
