package com.example.demo.entity;


import javax.persistence.*;
/**
 * @author 赵思阳
 * @since 2021-07-23
 * @version 1.0
 */

//商品
@Entity //表的实体
@Table(name="goodsItem")  //设置table
public class GoodsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增策
    private Integer sid;//商品条目id
    @Column(columnDefinition = "varchar(255) default ''")
    private String gname;//商品名？为什么商品名要叫gname，不该是sname吗？
    @Column(columnDefinition = "varchar(255) default ''")
    private Double transportfee=-1.0;//运费
    @Column(columnDefinition = "varchar(255) default ''")
    private String spic;//展示图
    @Column(columnDefinition = "varchar(255) default ''")
    private Double lowestprice;//最低价
    @Column(columnDefinition = "varchar(255) default ''")
    private Integer sales;//销量
    @Column(columnDefinition = "varchar(255) default ''")
    private Double favorablerate = 0.0;//好评率
    @Column(columnDefinition = "varchar(255) default ''")
    private Integer rateCount = 0; //评分人数

    public Integer getRateCount() {
        return rateCount;
    }

    public void setRateCount(Integer rateCount) {
        this.rateCount = rateCount;
    }

    @Column(columnDefinition = "varchar(255) default ''")
    private Integer merchantId;//店铺id
    @Column(columnDefinition = "varchar(255) default ''")
    private  String details;//详情
    @Column(columnDefinition = "varchar(255) default ''")
    private String type;//type

    @Column(columnDefinition = "default '1'")
    private Integer token = 1; //固定

    public Integer getToken()
    {
        return token;
    }

    public void setToken(Integer token)
    {
        this.token = token;
    }

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

    public Double getTransportfee() {
        return transportfee;
    }

    public void setTransportfee(Double transportfee) {
        this.transportfee = transportfee;
    }

    public String getSpic() {
        return spic;
    }

    public void setSpic(String spic) {
        this.spic = spic;
    }

    public Double getLowestprice()
    {
        return lowestprice;
    }

    public void setLowestprice(Double lowestprice)
    {
        this.lowestprice = lowestprice;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Double getFavorablerate() {
        return favorablerate;
    }

    public void setFavorablerate(Double favorablerate) {
        this.favorablerate = favorablerate;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
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
