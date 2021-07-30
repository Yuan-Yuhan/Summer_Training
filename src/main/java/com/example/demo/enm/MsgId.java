package com.example.demo.enm;

/**
 * @Author 赵思阳，袁宇涵
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
    GOODS_NOT_DETAILED(7,"请补全信息！"),


    FILE_NOT_EXIST(8,"上传失败，请选择文件！"),
    FILE_UPLOAD_FAILED(9,"上传失败！"),



    MERCHANT_NOT_EXIST(6,"商家不存在或者已注销！"),

    //----------------------袁宇涵的code区
    //----------------------袁宇涵的code区



    SYNC_FAULT_Favorite_Stars(66,"请保证数据库stars和favorite同步"),

    DATE_READ_FAIL(77,"读取日期失败"),

    NO_RESULT(89,"无结果"),

    SHANGPIN_NOT_EXIST(99,"商品不存在"),

    GUIGE_NOT_EXIST(88,"规格不存在"),


    ORDER_Lack_Paraments(101,"缺少一项或几项参数: 用户ID,商品ID,规格ID,商户ID,商品数量"),
    ORDER_Lack_OrderId(102,"你输入的OrderId在数据库中不存在"),
    ORDER_Lack_Status(103,"没有订单状态,无法更新"),
    ORDER_Lack_CommentFigures(104,"评论参数缺失"),
    ORDER_WRONG_Status(105,"错误的订单状态"),
    ORDER_BAD_Merchant(106,"没这个商家,或者这个商家还没有订单"),
    ORDER_GOODS_NOT_AVAILABLE(107,"商品库存不足"),
    ORDER_CANNOT_DELETE(108,"只有待支付和已完成的订单才能删除"),

    COUPON_Lack_Paraments(111,"优惠券缺少必要参数"),
    COUPON_False_ID(112,"缺少或错误的优惠券ID,请重新检查" ),

    UC_Lack_UID(121,"你没给我传用户的uid"),
    UC_Lack_CouponID(122,"你没给我传优惠券的couponId"),
    UC_False_UID(123,"你传了一个错误的UID"),
    UC_False_MerchantID(124,"你要么没传商户ID,要么是错的"),
    UC_Too_Cheap(125,"这么便宜的商品还要用优惠券?想屁吃!"),

    FAVORITE_WRONG_UID(201,"错误的UID"),
    FAVORITE_WRONG_SID(202,"错误的商品ID"),
    FAVORITE_WRONG_FavoriteID(205,"错误的FavoriteID"),
    FAVORITE_ALREADY_EXIST(206,"已经收藏了，不要重复收藏"),

    FAVORITE_NO_DATA(207,"啊咧,啥收藏都找不到!"),

    DELIVERY_Lack_deliveryID(401,"缺少送货地址ID"),
    DELIVERY_Lack_UID(402,"缺少用户ID"),
    DELIVERY_Lack_AddressInfo(403,"缺少送货地址详细信息"),

    VIP_Lack_UID(801,"缺少用户ID"),
    VIP_Lack_MerchantID(802,"缺少商户ID"),
    VIP_Lack_VIPID(803,"缺少VIP的ID"),
    VIP_WRONG_VIP_LEVEL(804,"未输入或错误的VIP等级"),
    VIP_WRONG_VIP_DISCOUNT(805,"未输入或错误的折扣"),
    VIP_ALREADY_EXIST(806,"已经是会员了"),


    Cart_NO_CartID(901,"不存在的购物车ID"),
    Cart_ALREADY_EXIST(909,"该商品在购物车中已存在"),

    TYPE_NO_MerchantID(1001,"缺少MerchantID"),
    TYPE_NO_TypeID(1002,"缺少TypeID"),

    ;



    //---------------------袁宇涵的code区截止

    //---------------------袁宇涵的code区截止

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
