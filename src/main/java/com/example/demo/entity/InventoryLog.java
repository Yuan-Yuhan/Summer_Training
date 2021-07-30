package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */

//出入库日志
@Entity
@Table(name = "inventoryLog")
public class InventoryLog
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId; //日志ID

    private String sname; //商品名

    private String gname; //规格名

    private Integer count; //数量

    private String inOrOut; // 出库?入库

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date; //时间

    private Integer merchantId;//商家ID

    public Integer getLogId()
    {
        return logId;
    }

    public void setLogId(Integer logId)
    {
        this.logId = logId;
    }

    public String getSname()
    {
        return sname;
    }

    public void setSname(String sname)
    {
        this.sname = sname;
    }

    public String getGname()
    {
        return gname;
    }

    public void setGname(String gname)
    {
        this.gname = gname;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public String getInOrOut()
    {
        return inOrOut;
    }

    public void setInOrOut(String inOrOut)
    {
        this.inOrOut = inOrOut;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Integer getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId)
    {
        this.merchantId = merchantId;
    }
}
