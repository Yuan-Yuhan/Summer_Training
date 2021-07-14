package com.example.demo.service;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.*;
import com.example.demo.repository.CouponRepository;
import com.example.demo.repository.GoodsItemRepository;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 袁宇涵
 * @since 2021/7/14
 * @version 1.0
 */
@Service
public class OrderService
{
    @Autowired
    OrderRepository orderRepository;
    GoodsRepository goodsRepository;
    GoodsItemRepository goodsItemRepository;
    CouponRepository couponRepository;


    /**
     *新增订单
     */
    //必传:用户ID,商品ID,规格ID,商户ID,商品数量
    public Result addOrder(Order order){
        //判断必传参数是否齐全
        if(order.getUid() == null || order.getSid() == null
        || order.getGid() == null || order.getMerchantId() == null
        || order.getGoodsCount() == null)
        {
            return ResultUtils.error(MsgId.ORDER_Lack_Paraments);
        }
        //设置订单初始状态
        order.setOrderStatus("未支付");
        //计算出订单总金额
        GoodsItem goodsItem = goodsItemRepository.findBySid(order.getSid());
        Goods goods = goodsRepository.findGoodsByGid(order.getGid());
        Coupon coupon = couponRepository.findCouponByCouponId(order.getCouponId());
            //1, 未使用优惠券
        if(order.getCouponId() == null)
        {
            //数量*原价*折扣+运费
            Double totalPrice = order.getGoodsCount()*goods.getOriginalPrice()*goods.getDiscount()
                    +goodsItem.getTransportfee();
        }
        //2, 使用优惠券
        else
        {
            //数量*原价*折扣+运费-优惠券
            Double totalPrice = order.getGoodsCount()*goods.getOriginalPrice()*goods.getDiscount()
                    +goodsItem.getTransportfee()
                    -coupon.getCouponMoney();
        }

        /**
         * order.setOrderDate..
         */

        orderRepository.save(order);

        return ResultUtils.success(order);
    }

    //删除订单
    //必传:订单ID
    public Result deleteOrder(Integer orderId)
    {
        Order order = orderRepository.findByOrderId(orderId);
        if(order.getOrderId()==null)
        {
            return ResultUtils.error(MsgId.ORDER_Lack_OrderId);
        }
        orderRepository.delete(order);
        return ResultUtils.success();
    }

    //修改订单状态
    //必传:订单ID,订单状态
    public Result updateOrderStatus(Order order)
    {
        if(order.getOrderId()==null)
        {
            return ResultUtils.error(MsgId.ORDER_Lack_OrderId);
        }
        if(order.getOrderStatus()==null)
        {
            return ResultUtils.error(MsgId.ORDER_Lack_Status);
        }
        //修改订单状态
        Order oldOrder = orderRepository.findByOrderId(order.getOrderId()); //读取这个ID订单的数据
        oldOrder.setOrderStatus(order.getOrderStatus()); //更新老订单的状态
        orderRepository.save(oldOrder);
        return ResultUtils.success(oldOrder);
    }

    //新增或修改评论
    //必传:订单ID,评分,评论内容,评论时间
    public Result updateOrderComment(Order order)
    {
        if(order.getOrderId()==null)
        {
            return ResultUtils.error(MsgId.ORDER_Lack_OrderId);
        }
        if(order.getCommentContent()==null || order.getCommentRate()==null || order.getCommentDate()==null)
        {
            return ResultUtils.error(MsgId.ORDER_Lack_CommentFigures);
        }
        Order oldOrder = orderRepository.findByOrderId(order.getOrderId());
        oldOrder.setCommentContent(order.getCommentContent());
        oldOrder.setCommentRate(order.getCommentRate());
        oldOrder.setCommentDate(order.getCommentDate());
        orderRepository.save(oldOrder);
        return ResultUtils.success(oldOrder);

    }
}
