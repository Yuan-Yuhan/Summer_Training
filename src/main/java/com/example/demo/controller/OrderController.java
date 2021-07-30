package com.example.demo.controller;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.Order;
import com.example.demo.entity.Result;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 袁宇涵
 * @since 2021/7/14
 * @version 1.0
 */

//订单
@RestController
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

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

    @RequestMapping("/findAllOrderByUid")
    public Result findAllOrderByUid(Integer uid)
    {
        return ResultUtils.success(orderRepository.findAllByUid(uid));
    }

    @RequestMapping("/findOrdersByMerId")
    public Result findAllOrderByMerchantId(Integer merchantId)
    {
        List<Order> orderList = orderRepository.findAllByMerchantId(merchantId);

        if(orderList == null)
            return ResultUtils.error(MsgId.ORDER_BAD_Merchant);

        return ResultUtils.success(orderList);
    }
    @RequestMapping("/findAllBySidAndOrderStatus")
    public Result findAllBySidAndOrderStatus(Integer sid,@RequestParam(defaultValue = "已完成") String orderStatus){
        return ResultUtils.success(orderRepository.findAllBySidAndOrderStatus(sid,orderStatus));
    }

    @RequestMapping("/get5YearsIncome")
    public Result get5YrsIncome(Integer merchantId)
    {
        return orderService.getFiveYearsIncome(merchantId);
    }

    @RequestMapping("/getMonthIncome")
    public Result getMonthIncome(Integer merchantId)
    {
        return orderService.getMonthIncomeThisYear(merchantId);
    }

    @RequestMapping("/getDailyAverageIncome")
    public Result getDailyAverage(Integer merchantId)
    {
        return orderService.getDailyAverageIncome(merchantId);
    }
}
