package com.example.demo.entity;

import javax.persistence.*;

/**
 * @author 袁宇涵
 * @since 2021/7/14
 * @version 1.0
 */

//用户-优惠券 关系数据
@Entity
@Table(name="UserCoupon")

public class UserCoupon
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ucId; //用户-优惠券 关系ID

    private Integer uid; //用户ID

    private Integer couponId;//优惠券ID

    private Integer merchantId;//商家ID

    private Double usableMoney;//起用金额

    public Double getUsableMoney()
    {
        return usableMoney;
    }

    public void setUsableMoney(Double usableMoney)
    {
        this.usableMoney = usableMoney;
    }

    public Integer getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId)
    {
        this.merchantId = merchantId;
    }

    public Integer getUcId()
    {
        return ucId;
    }

    public void setUcId(Integer ucId)
    {
        this.ucId = ucId;
    }

    public Integer getUid()
    {
        return uid;
    }

    public void setUid(Integer uid)
    {
        this.uid = uid;
    }

    public Integer getCouponId()
    {
        return couponId;
    }

    public void setCouponId(Integer couponId)
    {
        this.couponId = couponId;
    }
}