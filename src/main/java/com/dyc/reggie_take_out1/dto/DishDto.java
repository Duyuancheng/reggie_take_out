package com.dyc.reggie_take_out1.dto;


import com.dyc.reggie_take_out1.entity.Dish;
import com.dyc.reggie_take_out1.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
