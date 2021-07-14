package com.example.demo.entity;

import javax.persistence.*;

/**
 * @author 袁宇涵
 * @version 1.0
 * @since 2021/7/14
 */

@Entity
@Table(name = "ShopsFollowed")
public class ShopsFollowed
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer followId;//关注ID

    private Integer uid;//用户ID

    private Integer merchantId;//店铺ID

    public Integer getFollowId()
    {
        return followId;
    }

    public void setFollowId(Integer followId)
    {
        this.followId = followId;
    }

    public Integer getUid()
    {
        return uid;
    }

    public void setUid(Integer uid)
    {
        this.uid = uid;
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
