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
@RestController
@RequestMapping("/usercoupon")
public class UserCouponController
{
    @Autowired
    UserCouponService ucService;

    //优惠从商家那里获取优惠券
    @RequestMapping("/fetchCoupon")
    public Result fetchCoupon(Integer uid, Integer couponId, Integer merchantId)
    {
        return ucService.fetchCoupon(uid, couponId, merchantId);
    }

    //个人中心的用户的所有的优惠券
    @RequestMapping("/findCouponByUID")
    public Result findCouponByUID(Integer uid)
    {
        return ucService.findAllCouponByUid(uid);
    }


    //购物时获取可用的优惠券,仅当现价超过优惠券起用price时才显示
    @RequestMapping("/findCouponWhileShopping")
    public Result findCouponWhileShopping(Integer uid, Integer merchantId,Double price)
    {
        return ucService.findAllCouponWhileShopping(uid,merchantId,price);
    }
}
