package com.dyc.reggie_take_out1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyc.reggie_take_out1.entity.OrderDetail;
import com.dyc.reggie_take_out1.mapper.OrderDetailMapper;
import com.dyc.reggie_take_out1.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
