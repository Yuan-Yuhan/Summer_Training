package com.example.demo.repository;

import com.example.demo.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 袁宇涵
 * @since 2021-7-14
 * @version 1.0
 */
public interface CouponRepository extends JpaRepository<Coupon,Integer>
{
    Coupon findCouponByCouponId(int couponId);

    List<Coupon> findAllByMerchantId(Integer merchantId);
}
