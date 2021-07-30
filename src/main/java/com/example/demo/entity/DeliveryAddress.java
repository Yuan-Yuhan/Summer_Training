package com.example.demo.entity;

import javax.persistence.*;

/**
 * @Author 赵思阳
 * @Date 2021/7/14 10:06
 * @Version 1.0
 */

//运送地址
@Entity
@Table(name="deliveryAddress")
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增策略
    private Integer deliverId;//送货地址id

    private Integer uid;//用户id
    private String acceptPerson;//收货人
    private String acceptPhone;//收获手机
    private String acceptPlace;//三级行政区
    private String detailedAddress;//详细地址

    public Integer getDeliverId()
    {
        return deliverId;
    }

    public void setDeliverId(Integer deliverId)
    {
        this.deliverId = deliverId;
    }

    public Integer getUid()
    {
        return uid;
    }

    public void setUid(Integer uid)
    {
        this.uid = uid;
    }

    public String getAcceptPerson()
    {
        return acceptPerson;
    }

    public void setAcceptPerson(String acceptPerson)
    {
        this.acceptPerson = acceptPerson;
    }

    public String getAcceptPhone()
    {
        return acceptPhone;
    }

    public void setAcceptPhone(String acceptPhone)
    {
        this.acceptPhone = acceptPhone;
    }

    public String getAcceptPlace()
    {
        return acceptPlace;
    }

    public void setAcceptPlace(String acceptPlace)
    {
        this.acceptPlace = acceptPlace;
    }

    public String getDetailedAddress()
    {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress)
    {
        this.detailedAddress = detailedAddress;
    }
}
