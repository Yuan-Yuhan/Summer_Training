package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */

//广告
@Entity
@Table(name = "advertisement")
public class AD
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adId;//AD的主键

    private Integer merchantId; //商家ID

    private String picUrlOne;//第1图片URL

    private String webUrlOne;//第1网址URL

    private String picUrlTwo;//第2图片URL

    private String webUrlTwo;//第2网址URL

    private String picUrlThree;//第3图片URL

    private String webUrlThree;//第3网址URL

    public Integer getAdId()
    {
        return adId;
    }

    public void setAdId(Integer adId)
    {
        this.adId = adId;
    }

    public Integer getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId)
    {
        this.merchantId = merchantId;
    }

    public String getPicUrlOne()
    {
        return picUrlOne;
    }

    public void setPicUrlOne(String picUrlOne)
    {
        this.picUrlOne = picUrlOne;
    }

    public String getWebUrlOne()
    {
        return webUrlOne;
    }

    public void setWebUrlOne(String webUrlOne)
    {
        this.webUrlOne = webUrlOne;
    }

    public String getPicUrlTwo()
    {
        return picUrlTwo;
    }

    public void setPicUrlTwo(String picUrlTwo)
    {
        this.picUrlTwo = picUrlTwo;
    }

    public String getWebUrlTwo()
    {
        return webUrlTwo;
    }

    public void setWebUrlTwo(String webUrlTwo)
    {
        this.webUrlTwo = webUrlTwo;
    }

    public String getPicUrlThree()
    {
        return picUrlThree;
    }

    public void setPicUrlThree(String picUrlThree)
    {
        this.picUrlThree = picUrlThree;
    }

    public String getWebUrlThree()
    {
        return webUrlThree;
    }

    public void setWebUrlThree(String webUrlThree)
    {
        this.webUrlThree = webUrlThree;
    }
}
