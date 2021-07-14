package com.example.demo.repository;

import com.example.demo.entity.Coupon;
import com.example.demo.entity.Order;
import com.example.demo.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 袁宇涵
 * @since 2021/7/14
 * @version 1.0
 */
public interface UserCouponRepository extends JpaRepository<UserCoupon,Integer>
{
    List<UserCoupon> findAllByUid(Integer uid);

    List<UserCoupon> findAllByUidAndMerchantId(Integer uid, Integer merchantId);
}
