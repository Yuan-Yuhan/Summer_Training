package com.example.demo.enm;

/**
 * @author 赵思阳,袁宇涵
 * @since 2021/7/13
 * @version 1.x
 */
public enum MsgId {
    USER_ERR_PASS(1,"密码错误！"),
    USER_NOT_EXIST(2,"用户不存在！"),
    PHONE_ALREADY_EXIST(3,"注册失败，该电话号码已注册！"),
    USER_ALREADY_EXIST(4,"注册失败，该用户名已存在！"),
    NO_ID(5,"缺少Id参数！"),
    GOODS_NOT_EXIST(6,"该商品不存在或者已下架！"),
    GOODS_NOT_DETAILED(7,"请补全信息！"),


    DOT_NO_ID(2,"请不要传入ID，作为参数！"),
    NAME_TOO_LONG(9,"名字太长了！"),


    MERCHANT_NOT_EXIST(6,"商家不存在或者已注销！"),
    ORDERS_NOT_EXIST(7,"订单不存在或者被删除！"),
    RIDER_NOT_EXIST(8,"不存在该骑手！"),

    TOO_Expensive(10,"商品太贵了没钱就别买了！"),
    LACK_SHANGJIA_ID(11,"请输入查询商家id！"),


    //----------------------袁宇涵的code区
    ORDER_Lack_Paraments(101,"缺少一项或几项参数: 用户ID,商品ID,规格ID,商户ID,商品数量"),
    ORDER_Lack_OrderId(102,"你输入的OrderId在数据库中不存在"),
    ORDER_Lack_Status(103,"没有订单状态,无法更新"),
    ORDER_Lack_CommentFigures(104,"评论参数缺失"),

    COUPON_Lack_Paraments(111,"优惠券缺少必要参数"),
    COUPON_False_ID(112,"缺少或错误的优惠券ID,请重新检查" ),

    UC_Lack_UID(121,"你没给我传用户的uid"),
    UC_Lack_CouponID(122,"你没给我传优惠券的couponId"),
    UC_False_UID(123,"你传了一个错误的UID"),
    UC_False_MerchantID(124,"你要么没传商户ID,要么是错的"),
    UC_Too_Cheap(125,"这么便宜的商品还要用优惠券?想屁吃!"),
    ;


    //---------------------袁宇涵的code区截止

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
