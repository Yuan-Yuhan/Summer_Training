package com.example.demo.entity;

import javax.persistence.*;
/**
 * @author 赵思阳
 * @since 2021-07-23
 * @version 1.0
 */
//商品规格
@Entity //表的实体
@Table(name="goods")  //设置table
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增策略
    private Integer gid;//规格ID
    @Column(columnDefinition = "varchar(255) default ''")
    private Integer sid;//商品id
    @Column(columnDefinition = "varchar(255) default ''")
    private String gpic;//规格图
    @Column(columnDefinition = "varchar(255) default ''")
    private String specification;//规格描述
    @Column(columnDefinition = "varchar(255) default ''")
    private Double originalPrice;//原价
    @Column(columnDefinition = "varchar(255) default ''")
    private Double discount=1.0;//折扣
    private Double price;//现价
    @Column(columnDefinition = "varchar(255) default ''")
    private Integer inventory;//库存量

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getGpic() {
        return gpic;
    }

    public void setGpic(String gpic) {
        this.gpic = gpic;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}
