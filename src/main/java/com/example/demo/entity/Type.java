package com.example.demo.entity;

import javax.persistence.*;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */
//商品类型
@Entity
@Table(name = "type")
public class Type
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeId;//类型ID

    private Integer merchantId; //商户ID

    private String firstType; //一级名

    private String secondType;//二级名

    public Integer getTypeId()
    {
        return typeId;
    }

    public void setTypeId(Integer typeId)
    {
        this.typeId = typeId;
    }

    public Integer getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId)
    {
        this.merchantId = merchantId;
    }

    public String getFirstType()
    {
        return firstType;
    }

    public void setFirstType(String firstType)
    {
        this.firstType = firstType;
    }

    public String getSecondType()
    {
        return secondType;
    }

    public void setSecondType(String secondType)
    {
        this.secondType = secondType;
    }
}
