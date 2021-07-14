package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 袁宇涵
 * @version 1.0
 * @since 2021/7/14
 */

@Entity
@Table(name = "UserMessages")
public class UserMessages
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userMessageId;

    private Integer merchantId;//商户ID

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date messageUpdated;//更新时间

    public Integer getUserMessageId()
    {
        return userMessageId;
    }

    public void setUserMessageId(Integer userMessageId)
    {
        this.userMessageId = userMessageId;
    }

    public Integer getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId)
    {
        this.merchantId = merchantId;
    }

    public Date getMessageUpdated()
    {
        return messageUpdated;
    }

    public void setMessageUpdated(Date messageUpdated)
    {
        this.messageUpdated = messageUpdated;
    }
}
