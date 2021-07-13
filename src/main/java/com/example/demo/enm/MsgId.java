package com.example.demo.enm;

/**
 * @Author 赵思阳
 * @Date 2021/7/13 9:38
 * @Version 1.0
 */
public enum MsgId {
    USER_ERR_PASS(1,"密码错误！"),
    USER_NOT_EXIST(2,"用户不存在！"),
    PHONE_ALREADY_EXIST(3,"注册失败，该电话号码已注册！"),
    USER_ALREADY_EXIST(4,"注册失败，该用户名已存在！"),
    NO_ID(5,"缺少Id参数！"),
    GOODS_NOT_EXIST(6,"该商品不存在或者已下架！"),


    DOT_NO_ID(2,"请不要传入ID，作为参数！"),
    NAME_TOO_LONG(9,"名字太长了！"),


    MERCHANT_NOT_EXIST(6,"商家不存在或者已注销！"),
    ORDERS_NOT_EXIST(7,"订单不存在或者被删除！"),
    RIDER_NOT_EXIST(8,"不存在该骑手！"),

    TOO_Expensive(10,"商品太贵了没钱就别买了！"),
    LACK_SHANGJIA_ID(11,"请输入查询商家id！"),
    ;
    private Integer code;
    private String msg;

    //构造函数
    MsgId(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
