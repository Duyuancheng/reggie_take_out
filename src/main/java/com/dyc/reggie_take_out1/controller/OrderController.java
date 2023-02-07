package com.dyc.reggie_take_out1.controller;

import com.dyc.reggie_take_out1.common.Result;
import com.dyc.reggie_take_out1.entity.Orders;
import com.dyc.reggie_take_out1.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public Result<String> submit(@RequestBody Orders orders){
        log.info("订单数据{}",orders);
        ordersService.submit(orders);
        return Result.success("下单成功");
    }
}
