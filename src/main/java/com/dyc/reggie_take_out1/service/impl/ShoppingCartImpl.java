package com.dyc.reggie_take_out1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyc.reggie_take_out1.entity.ShoppingCart;
import com.dyc.reggie_take_out1.mapper.ShoppingCartMapper;
import com.dyc.reggie_take_out1.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
