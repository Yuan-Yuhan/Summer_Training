package com.example.demo.entity;

import javax.persistence.*;

/**
 * @author 袁宇涵
 * @since 2021/7/14
 * @version 1.0
 */

@Entity
@Table(name="UserCoupon")

public class UserCoupon
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ucId;

    private Integer uid;

    private Integer couponId;

    private Integer merchantId;

    private Double usableMoney;

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