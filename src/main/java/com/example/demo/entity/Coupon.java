package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 袁宇涵
 * @since 2021/7/14
 * @version 1.0
 */
@Entity //表的实体
@Table(name="Coupon")  //优惠券
public class Coupon
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer couponId;//优惠券ID

    private String couponIntro;//优惠券描述

    private Double usableMoney;//可用金额起点

    private Double couponMoney;//优惠金额

    private Integer usableStart;//使用日期起始

    private Integer usableEnd;//使用日期结束

    private Integer merchantId;//商户ID

    public Integer getCouponId()
    {
        return couponId;
    }

    public void setCouponId(Integer couponId)
    {
        this.couponId = couponId;
    }

    public String getCouponIntro()
    {
        return couponIntro;
    }

    public void setCouponIntro(String couponIntro)
    {
        this.couponIntro = couponIntro;
    }

    public Double getUsableMoney()
    {
        return usableMoney;
    }

    public void setUsableMoney(Double usableMoney)
    {
        this.usableMoney = usableMoney;
    }

    public Double getCouponMoney()
    {
        return couponMoney;
    }

    public void setCouponMoney(Double couponMoney)
    {
        this.couponMoney = couponMoney;
    }

    public Integer getUsableStart()
    {
        return usableStart;
    }

    public void setUsableStart(Integer usableStart)
    {
        this.usableStart = usableStart;
    }

    public Integer getUsableEnd()
    {
        return usableEnd;
    }

    public void setUsableEnd(Integer usableEnd)
    {
        this.usableEnd = usableEnd;
    }

    public Integer getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId)
    {
        this.merchantId = merchantId;
    }
}
