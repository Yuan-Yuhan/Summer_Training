package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author 袁宇涵
 * @Date 2021/7/13 15:17
 * @Version 1.0
 */
@Entity //表的实体
@Table(name="Order")  //订单
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增策略
    private Integer orderId;//订单ID

    private Integer uid; //用户ID

    private Integer sid;//商品ID

    private Integer gid;//规格ID

    private Integer merchantId;//商户ID

    private Integer goodsCount;//商品数量

    private String orderNote; //留言(生产订单时)

    private String orderStatus;//状态

    private Integer couponId;//优惠券ID

    private Integer commentRate; //打分1~5

    private String commentContent;//评论内容

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updated;//更新时间


    public Integer getOrderId()
    {
        return orderId;
    }

    public void setOrderId(Integer orderId)
    {
        this.orderId = orderId;
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

    public Integer getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId)
    {
        this.merchantId = merchantId;
    }

    public Integer getGoodsCount()
    {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount)
    {
        this.goodsCount = goodsCount;
    }

    public String getOrderNote()
    {
        return orderNote;
    }

    public void setOrderNote(String orderNote)
    {
        this.orderNote = orderNote;
    }

    public String getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public Integer getCouponId()
    {
        return couponId;
    }

    public void setCouponId(Integer couponId)
    {
        this.couponId = couponId;
    }

    public Integer getCommentRate()
    {
        return commentRate;
    }

    public void setCommentRate(Integer commentRate)
    {
        this.commentRate = commentRate;
    }

    public String getCommentContent()
    {
        return commentContent;
    }

    public void setCommentContent(String commentContent)
    {
        this.commentContent = commentContent;
    }

    public Date getUpdated()
    {
        return updated;
    }

    public void setUpdated(Date updated)
    {
        this.updated = updated;
    }
}
