package com.example.demo.entity;

import javax.persistence.*;

/**
 * @Author 赵思阳
 * @Date 2021/7/14 10:17
 * @Version 1.0
 */

//会员
@Entity
@Table(name="vip")
public class VIP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增策略
    private Integer vipId;//会员id


    private Integer merchantId;//商户id
    private Integer uid;//用户id
    private Integer VipLevel = 1;//等级
    private Double VipDiscount = 1.0;//会员折扣

    public Integer getvVid() {
        return vipId;
    }

    public void setvVid(Integer vId) {
        this.vipId = vId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getVipLevel() {
        return VipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        VipLevel = vipLevel;
    }

    public Double getVipDiscount() {
        return VipDiscount;
    }

    public void setVipDiscount(Double vipDiscount) {
        VipDiscount = vipDiscount;
    }
}
