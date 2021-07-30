package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 袁宇涵
 * @since 2021/7/14
 * @version 1.0
 */
//用户和优惠券的对应关系
@RestController
@RequestMapping("/usercoupon")
public class UserCouponController
{
    @Autowired
    UserCouponService ucService;

    //用户从商家那里获取优惠券
    @RequestMapping("/fetchCoupon")
    public Result fetchCoupon(Integer uid, Integer couponId, Integer merchantId)
    {
        return ucService.fetchCoupon(uid, couponId, merchantId);
    }

    //用户在个人中心查找所有的优惠券
    @RequestMapping("/findCouponByUID")
    public Result findCouponByUID(Integer uid)
    {
        return ucService.findAllCouponByUid(uid);
    }


    //购物时显示可用的优惠券,仅当现价超过优惠券起用价时才显示
    @RequestMapping("/findCouponWhileShopping")
    public Result findCouponWhileShopping(Integer uid, Integer merchantId,Double price)
    {
        return ucService.findAllCouponWhileShopping(uid,merchantId,price);
    }
}
