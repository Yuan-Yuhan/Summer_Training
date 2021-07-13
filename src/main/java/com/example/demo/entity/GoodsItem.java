package com.example.demo.entity;


import javax.persistence.*;

@Entity //表的实体
@Table(name="商品")  //设置table
public class GoodsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增策
    private Integer sid;//商品条目id
    @Column(columnDefinition = "varchar(255) default ''")
    private String gname;//商品名
    @Column(columnDefinition = "varchar(255) default ''")
    private double transportfee;//运费
    @Column(columnDefinition = "varchar(255) default ''")
    private String spic;//展示图
    @Column(columnDefinition = "varchar(255) default ''")
    private String lowestprice;//最低价
    @Column(columnDefinition = "varchar(255) default ''")
    private int sales;//销量
    @Column(columnDefinition = "varchar(255) default ''")
    private double favorablerate;//好评率
    @Column(columnDefinition = "varchar(255) default ''")
    private int shopid;//店铺id
    @Column(columnDefinition = "varchar(255) default ''")
    private  String details;//详情
    @Column(columnDefinition = "varchar(255) default ''")
    private String type;//type


    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public double getTransportfee() {
        return transportfee;
    }

    public void setTransportfee(double transportfee) {
        this.transportfee = transportfee;
    }

    public String getSpic() {
        return spic;
    }

    public void setSpic(String spic) {
        this.spic = spic;
    }

    public String getLowestprice() {
        return lowestprice;
    }

    public void setLowestprice(String lowestprice) {
        this.lowestprice = lowestprice;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public double getFavorablerate() {
        return favorablerate;
    }

    public void setFavorablerate(double favorablerate) {
        this.favorablerate = favorablerate;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
