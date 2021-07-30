package com.example.demo.entity;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;

/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */

//店铺首页的轮播图展示
@Entity
@Table(name = "showcase")
public class Showcase
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showcaseId; //展示柜ID

    private Integer merchantId; //商家ID

    private Integer sidOne; //商品1 ID

    private Integer sidTwo; //商品2 ID

    private Integer sidThree; //商品3 ID

    public Integer getShowcaseId()
    {
        return showcaseId;
    }

    public void setShowcaseId(Integer showcaseId)
    {
        this.showcaseId = showcaseId;
    }

    public Integer getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId)
    {
        this.merchantId = merchantId;
    }

    public Integer getSidOne()
    {
        return sidOne;
    }

    public void setSidOne(Integer sidOne)
    {
        this.sidOne = sidOne;
    }

    public Integer getSidTwo()
    {
        return sidTwo;
    }

    public void setSidTwo(Integer sidTwo)
    {
        this.sidTwo = sidTwo;
    }

    public Integer getSidThree()
    {
        return sidThree;
    }

    public void setSidThree(Integer sidThree)
    {
        this.sidThree = sidThree;
    }
}
