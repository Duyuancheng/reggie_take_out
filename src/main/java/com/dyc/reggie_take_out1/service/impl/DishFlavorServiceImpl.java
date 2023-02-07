package com.dyc.reggie_take_out1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyc.reggie_take_out1.entity.DishFlavor;
import com.dyc.reggie_take_out1.mapper.DishFlavorMapper;
import com.dyc.reggie_take_out1.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
