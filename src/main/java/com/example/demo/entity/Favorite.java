package com.example.demo.entity;


import javax.persistence.*;

/**
 * @author 袁宇涵
 * @version 1.0
 * @since 2021/7/14
 */

//收藏商品
@Entity
@Table(name = "Favorites")
public class Favorite
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoriteId;//收藏ID

    private Integer uid;//用户ID

    private Integer sid;//商品ID

    private Integer merchantId;//商户ID
    //标识这个商品是来自哪个商户的

    private String gname; //商品名

    public String getGname()
    {
        return gname;
    }

    public void setGname(String gname)
    {
        this.gname = gname;
    }

    public Integer getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId)
    {
        this.merchantId = merchantId;
    }

    public Integer getFavoriteId()
    {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoritesId)
    {
        this.favoriteId = favoritesId;
    }

    public Integer getUid()
    {
        return uid;
    }

    public void setUid(Integer uid)
    {
        this.uid = uid;
    }

    public Integer getSid()
    {
        return sid;
    }

    public void setSid(Integer sid)
    {
        this.sid = sid;
    }
}
