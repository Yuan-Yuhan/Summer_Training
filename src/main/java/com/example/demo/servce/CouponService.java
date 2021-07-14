package com.example.demo.servce;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.Coupon;
import com.example.demo.entity.Result;
import com.example.demo.repository.CouponRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Result addOne(Coupon coupon)
    {
        if(coupon.getCouponIntro() == null || coupon.getUsableMoney() == null
                || coupon.getUsableStart() == null || coupon.getUsableEnd() == null
                || coupon.getMerchantId() == null || coupon.getCouponMoney() == null)
        {
            return ResultUtils.error(MsgId.COUPON_Lack_Paraments);
        }
        couponRepository.save(coupon);
        return ResultUtils.success();
    }

    public Result update(Coupon coupon)
    {
        if(coupon.getCouponId() == null)
            return ResultUtils.error(MsgId.COUPON_Lack_ID);
        if(coupon.getCouponIntro() == null || coupon.getUsableMoney() == null
                || coupon.getUsableStart() == null || coupon.getUsableEnd() == null
                || coupon.getMerchantId() == null || coupon.getCouponMoney() == null)
        {
            return ResultUtils.error(MsgId.COUPON_Lack_Paraments);
        }
        couponRepository.save(coupon);
        return ResultUtils.success();
    }

    public Result delete(int couponId)
    {
        Coupon coupon = couponRepository.findCouponByCouponId(couponId);
        if(coupon == null)
            return ResultUtils.error(MsgId.NO_ID);

        couponRepository.delete(coupon);
        return ResultUtils.success();
    }
}
