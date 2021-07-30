package com.example.demo.entity;

import javax.persistence.*;

/**
 * @Author 赵思阳
 * @Date 2021/7/13 16:26
 * @Version 1.0
 */

//商家
@Entity
@Table(name="merchant")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增策略
    private Integer merchantId;//商户id
    @Column(columnDefinition = "varchar(255) default ''")
    private String merchantPhone;//商户电话
    @Column(columnDefinition = "varchar(255) default ''")
    private String merchantName;//商户名/店铺名
    @Column(columnDefinition = "varchar(255) default ''")
    private String merchantPlace;//所在地
    @Column(columnDefinition = "varchar(255) default ''")
    private String merchantPassword;//密码
    @Column(columnDefinition = "varchar(255) default ''")
    private String merchantPic;//店铺图

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantPlace() {
        return merchantPlace;
    }

    public void setMerchantPlace(String merchantPlace) {
        this.merchantPlace = merchantPlace;
    }

    public String getMerchantPassword() {
        return merchantPassword;
    }

    public void setMerchantPassword(String merchantPassword) {
        this.merchantPassword = merchantPassword;
    }

    public String getMerchantPic() {
        return merchantPic;
    }

    public void setMerchantPic(String merchantPic) {
        this.merchantPic = merchantPic;
    }
}
