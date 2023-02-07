package com.dyc.reggie_take_out1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dyc.reggie_take_out1.entity.Orders;

public interface OrdersService extends IService<Orders> {

    public void submit(Orders orders);


}
