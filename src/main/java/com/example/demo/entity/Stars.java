package com.example.demo.entity;

import javax.persistence.*;

/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */

//商品收藏的相关信息
@Entity
@Table(name = "stars")
public class Stars
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer starId; //星星ID

    public Integer getStarId()
    {
        return starId;
    }

    public void setStarId(Integer starId)
    {
        this.starId = starId;
    }

    private String gname; // 商品名

    private Integer starNum; //星星数,即被收藏数

    private Integer merchantId;//商户ID

    public Integer getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId)
    {
        this.merchantId = merchantId;
    }

    public String getGname()
    {
        return gname;
    }

    public void setGname(String gname)
    {
        this.gname = gname;
    }

    public Integer getStarNum()
    {
        return starNum;
    }

    public void setStarNum(Integer starNum)
    {
        this.starNum = starNum;
    }
}
