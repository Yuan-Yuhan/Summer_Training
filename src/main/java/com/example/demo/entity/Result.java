package com.example.demo.entity;

import java.io.Serializable;

/**
 * @Author 赵思阳
 * @Date 2021/7/13 9:33
 * @Version 1.0
 */
public class Result<T> implements Serializable {
    private Integer code;//正确码、错误码
    private String msg;//正确信息、错误信息
    private T date;//数据

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
