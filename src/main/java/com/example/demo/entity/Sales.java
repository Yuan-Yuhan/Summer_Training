package com.example.demo.entity;


/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */
//商品的销量
public class Sales
{
    private String gname; //商品名

    private Integer sales;//销量

    public String getGname()
    {
        return gname;
    }

    public void setGname(String gname)
    {
        this.gname = gname;
    }

    public Integer getSales()
    {
        return sales;
    }

    public void setSales(Integer sales)
    {
        this.sales = sales;
    }
}
