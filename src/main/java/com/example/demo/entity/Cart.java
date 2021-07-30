package com.example.demo.entity;


import javax.persistence.*;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */

//购物车
@Entity
@Table(name = "Cart")
public class Cart
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;//购物车ID

    private Integer uid; //用户ID

    private Integer sid;//商品ID

    private String gname;//商品名

    public String getGname()
    {
        return gname;
    }

    public void setGname(String gname)
    {
        this.gname = gname;
    }

    private Integer gid;//规格ID

    public Integer getCartId()
    {
        return cartId;
    }

    public void setCartId(Integer cartId)
    {
        this.cartId = cartId;
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

    public Integer getGid()
    {
        return gid;
    }

    public void setGid(Integer gid)
    {
        this.gid = gid;
    }
}
