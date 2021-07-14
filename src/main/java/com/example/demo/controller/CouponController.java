package com.example.demo.controller;

import com.example.demo.entity.Coupon;
import com.example.demo.entity.Result;
import com.example.demo.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 袁宇涵
 * @since 2021-7-14
 * @version 1.0
 */

@RestController
@RequestMapping("/coupon")
public class CouponController
{
    @Autowired
    CouponService couponService;

    @RequestMapping("/addCoupon")
    public Result addCoupon(Coupon coupon)
    {
        return couponService.addCoupon(coupon);
    }

    @RequestMapping("/updateCoupon")
    public Result updateCoupon(Coupon coupon)
    {
        return couponService.updateCoupon(coupon);
    }

    @RequestMapping("/deleteCoupon")
    public Result deleteCoupon(Integer couponId)
    {
        return couponService.deleteCoupon(couponId);
    }
}
