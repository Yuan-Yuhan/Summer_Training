package com.example.demo.entity;

import javax.persistence.*;

@Entity //表的实体
@Table(name="goods")  //设置table
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增策略
    private Integer gid;//规格ID
    @Column(columnDefinition = "varchar(255) default ''")
    private String sid;//商品id
    @Column(columnDefinition = "varchar(255) default ''")
    private String gpic;//规格图
    @Column(columnDefinition = "varchar(255) default ''")
    private String specification;//规格描述
    @Column(columnDefinition = "varchar(255) default ''")
    private double price;//原价
    @Column(columnDefinition = "varchar(255) default ''")
    private double discount;//折扣
    @Column(columnDefinition = "varchar(255) default ''")
    private int inventory;//库存量

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
