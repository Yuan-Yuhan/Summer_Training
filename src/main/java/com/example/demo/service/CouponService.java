package com.example.demo.service;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.Coupon;
import com.example.demo.entity.Result;
import com.example.demo.repository.CouponRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author 袁宇涵
 * @since 2021-7-14
 * @version 1.0
 */
@Service
public class CouponService
{
    @Autowired
    CouponRepository couponRepository;

    /**
     * 必要参数: 优惠券介绍,优惠金额,满xx元才优惠,商户ID,起始日期,截止日期
     * @param coupon
     * @return
     */
    public Result addCoupon(Coupon coupon)
    {
        //判断优惠券的必传参数是否缺少
        if(coupon.getCouponIntro() == null || coupon.getUsableMoney() == null
                || coupon.getUsableStart() == null || coupon.getUsableEnd() == null
                || coupon.getMerchantId() == null || coupon.getCouponMoney() == null)
        {
            return ResultUtils.error(MsgId.COUPON_Lack_Paraments);
        }
        couponRepository.save(coupon);
        return ResultUtils.success(coupon);
    }

    //必要参数: 优惠券ID,优惠券介绍,优惠金额,满xx元才优惠,商户ID,起始日期,截止日期
    public Result updateCoupon(Coupon coupon)
    {
        //判断优惠券ID是否缺少
        if(coupon.getCouponId() == null)
            return ResultUtils.error(MsgId.COUPON_False_ID);
        //判断其他必传参数是否缺少
        if(coupon.getCouponIntro() == null || coupon.getUsableMoney() == null
                || coupon.getUsableStart() == null || coupon.getUsableEnd() == null
                || coupon.getMerchantId() == null || coupon.getCouponMoney() == null)
        {
            return ResultUtils.error(MsgId.COUPON_Lack_Paraments);
        }
        couponRepository.save(coupon);
        return ResultUtils.success(coupon);
    }

    public Result deleteCoupon(int couponId)
    {
        Coupon coupon = couponRepository.findCouponByCouponId(couponId);
        if(coupon == null)
            return ResultUtils.error(MsgId.COUPON_False_ID);

        couponRepository.delete(coupon);
        return ResultUtils.success();
    }
}
