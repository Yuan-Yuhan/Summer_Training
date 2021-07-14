package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.entity.Result;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 袁宇涵
 * @since 2021/7/14
 * @version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    OrderService orderService;

    @RequestMapping("/addOrder")
    public Result addOrder(Order order)
    {
        return orderService.addOrder(order);
    }

    @RequestMapping("/deleteOrder")
    public Result deleteOrder(Integer orderID)
    {
        return orderService.deleteOrder(orderID);
    }

    @RequestMapping("/updateStatus")
    public Result updateOrderStatus(Order order)
    {
        return orderService.updateOrderStatus(order);
    }

    @RequestMapping("/updateComment")
    public Result updateComment(Order order)
    {
        return orderService.updateOrderComment(order);
    }
}
