package com.example.demo.service;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.*;
import com.example.demo.repository.CouponRepository;
import com.example.demo.repository.MerchantRepository;
import com.example.demo.repository.UserCouponRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 袁宇涵
 * @since 2021/7/14
 * @version 1.0
 */
@Service
public class UserCouponService
{
    @Autowired
    UserCouponRepository ucRepository;
    UserRepository userRepository;
    CouponRepository couponRepository;
    MerchantRepository merchantRepository;

    //用户领取优惠券
    //必传: UID,couponID,merchantID
    public Result fetchCoupon(Integer uid, Integer couponId, Integer merchantId)
    {
        //检查UID,couponID,merchantID的正确性
        User user = userRepository.findByUid(uid);
        Coupon coupon = couponRepository.findCouponByCouponId(couponId);
        Merchant merchant = merchantRepository.findMerchantByMerchantId(merchantId);
        if(user.getUid()==null)
            return ResultUtils.error(MsgId.UC_Lack_UID);
        else if(coupon.getCouponId()==null)
            return ResultUtils.error(MsgId.UC_Lack_CouponID);
        else if(merchant==null)
            return ResultUtils.error(MsgId.UC_False_MerchantID);

        UserCoupon uc = new UserCoupon();
        uc.setUid(uid);
        uc.setCouponId(couponId);
        uc.setMerchantId(merchantId);
        uc.setUsableMoney(coupon.getUsableMoney());

        ucRepository.save(uc);
        return ResultUtils.success();
    }

    //获取用户所有的优惠券(还没判断时间)
    //必传:UID
    public Result findAllCouponByUid(Integer uid)
    {
        User user = userRepository.findByUid(uid);
        //新用法!!!!!!
        if(user==null)
            return ResultUtils.error(MsgId.UC_False_UID);

        return ResultUtils.success(ucRepository.findAllByUid(uid));
    }

    //购物时获取用户在这个店铺可用的优惠券(还没判断时间,级联)
    //必传UID,merchantId,现价price
    public Result findAllCouponWhileShopping(Integer uid, Integer merchantId, Double price)
    {
        User user = userRepository.findByUid(uid);
        //新用法!!!!!!
        if(user==null)
            return ResultUtils.error(MsgId.UC_False_UID);

        Merchant merchant = merchantRepository.findMerchantByMerchantId(merchantId);
        //新用法!!!!!!
        if(merchant==null)
            return ResultUtils.error(MsgId.UC_False_MerchantID);



        List<UserCoupon> templeList = ucRepository.findAllByUidAndMerchantId(uid,merchantId);

        List<UserCoupon> newList = null;
        //筛选掉不符合金额起点要求的优惠券
        for(UserCoupon userCoupon:templeList)
        {
            if(userCoupon.getUsableMoney()<=price)
                newList.add(userCoupon);
        }

        return ResultUtils.success(newList);



    }

}
