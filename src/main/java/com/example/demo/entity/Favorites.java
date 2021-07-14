package com.example.demo.entity;


import javax.persistence.*;

/**
 * @author 袁宇涵
 * @version 1.0
 * @since 2021/7/14
 */

@Entity
@Table(name = "Favorites")
public class Favorites
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoritesId;//收藏ID

    private Integer uid;//用户ID

    private Integer sid;//商品ID

    public Integer getFavoritesId()
    {
        return favoritesId;
    }

    public void setFavoritesId(Integer favoritesId)
    {
        this.favoritesId = favoritesId;
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
