package com.dyc.reggie_take_out1.dto;


import com.dyc.reggie_take_out1.entity.Setmeal;
import com.dyc.reggie_take_out1.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
