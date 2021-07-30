package com.example.demo.service;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 袁宇涵
 * @since 2021/7/14
 * @version 1.5
 */

//订单类
@Service
public class OrderService
{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    GoodsItemRepository goodsItemRepository;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    MerchantRepository merchantRepository;
    @Autowired
    InventoryLogRepository inventoryLogRepository;


    /**
     * 新增订单
     */
    //必传:用户ID,商品ID,规格ID,商户ID,商品数量
    //可选:优惠券ID
    public Result addOrder(Order order)
    {
        //判断必传参数是否齐全
        if ( order.getUid() == null || order.getSid() == null
                || order.getGid() == null || order.getMerchantId() == null
                || order.getGoodsCount() == null )
        {
            return ResultUtils.error(MsgId.ORDER_Lack_Paraments);
        }
        Goods goods = goodsRepository.findGoodsByGid(order.getGid());

        if ( goods.getInventory() <= order.getGoodsCount() )
            return ResultUtils.error(MsgId.ORDER_GOODS_NOT_AVAILABLE);


        //设置订单初始状态
        order.setOrderStatus("未支付");
        //计算出订单总金额
        GoodsItem goodsItem = goodsItemRepository.findBySid(order.getSid());

        Double totalPrice = order.getGoodsCount() * goods.getOriginalPrice() * goods.getDiscount()
                + goodsItem.getTransportfee();
        order.setTotalPrice(totalPrice);

//        Coupon coupon = couponRepository.findCouponByCouponId(order.getCouponId());
//        //1, 未使用优惠券
//        if ( order.getCouponId() == null )
//        {
//            //数量*原价*折扣+运费

//
//        }
//        //2, 使用优惠券
//        else
//        {
//            //数量*原价*折扣+运费-优惠券
//            Double totalPrice = order.getGoodsCount() * goods.getOriginalPrice() * goods.getDiscount()
//                    + goodsItem.getTransportfee()
//                    - coupon.getCouponMoney();
//            order.setTotalPrice(totalPrice);
//        }

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
        if ( order.getOrderId() == null )
        {
            return ResultUtils.error(MsgId.ORDER_Lack_OrderId);
        }
        String status = order.getOrderStatus();
        if ( !(status.equals("未支付") || status.equals("已完成")) )
            return ResultUtils.error(MsgId.ORDER_CANNOT_DELETE);
        orderRepository.delete(order);
        return ResultUtils.success();
    }

    //修改订单状态
    //必传:订单ID,订单状态
    public Result updateOrderStatus(Order order)
    {
        if ( order.getOrderId() == null )
        {
            return ResultUtils.error(MsgId.ORDER_Lack_OrderId);
        }

        String status = order.getOrderStatus();

        if ( status == null )
        {
            return ResultUtils.error(MsgId.ORDER_Lack_Status);
        }
        if ( !status.equals("待发货") && !status.equals("待收货") && !status.equals("已完成") && !status.equals("未支付") )
        {
            return ResultUtils.error(MsgId.ORDER_WRONG_Status);
        }
        //修改订单状态
        Order oldOrder = orderRepository.findByOrderId(order.getOrderId()); //读取这个ID订单的数据
        oldOrder.setOrderStatus(order.getOrderStatus()); //更新老订单的状态


        //当订单状态为 "待收货"时,库存-1
        //并保存出库日志
        if ( status.equals("待收货") )
        {
            Integer gid = oldOrder.getGid();
            Goods goods = goodsRepository.findGoodsByGid(gid);
            goods.setPrice(goods.getPrice() - 1); //库存-1
            goodsRepository.save(goods);

            //保存出库日志
            InventoryLog inventoryLog = new InventoryLog();

            inventoryLog.setCount(oldOrder.getGoodsCount());
            inventoryLog.setGname(goodsRepository.findGoodsByGid(oldOrder.getGid()).getSpecification());
            inventoryLog.setSname(goodsItemRepository.findBySid(oldOrder.getSid()).getGname());
            inventoryLog.setInOrOut("出库");

            inventoryLog.setMerchantId(goodsItemRepository.findBySid(oldOrder.getSid()).getMerchantId());
            //保存出入库日志
            inventoryLogRepository.save(inventoryLog);


        }

        //当订单状态为"已完成"时,商品销量+1
        //!!!!!!!!!!!!!!商户营业额 += 订单金额!!!!!!!!!!
        if ( status.equals("已完成") )
        {
            Integer sid = oldOrder.getSid();
            GoodsItem goodsItem = goodsItemRepository.findBySid(sid);
            goodsItem.setSales(goodsItem.getSales() + 1);//销量+1
            goodsItemRepository.save(goodsItem);
            oldOrder.setCommentContent("系统默认好评");
            oldOrder.setCommentRate(5);
        }

        orderRepository.save(oldOrder);

        return ResultUtils.success();
    }

    //新增或修改评论,并修改商品好评率
    //必传:订单ID,评分,评论内容,评论时间
    public Result updateOrderComment(Order order)
    {
        if ( order.getOrderId() == null )
        {
            return ResultUtils.error(MsgId.ORDER_Lack_OrderId);
        }
        if ( order.getCommentContent() == null || order.getCommentRate() == null || order.getCommentDate() == null )
        {
            return ResultUtils.error(MsgId.ORDER_Lack_CommentFigures);
        }
        Order oldOrder = orderRepository.findByOrderId(order.getOrderId());
        oldOrder.setCommentContent(order.getCommentContent());
        oldOrder.setCommentRate(order.getCommentRate());
        oldOrder.setCommentDate(order.getCommentDate());
        //保存新的订单信息
        orderRepository.save(oldOrder);

        //获取订单的商品ID
        Integer sid = oldOrder.getSid();
        //通过商品ID搜索商品条目
        GoodsItem goodsItem = goodsItemRepository.findBySid(sid);

        //获取这个商品的评分
        Double oldRate = goodsItem.getFavorablerate();
        //获取评分人数
        Integer rateCount = goodsItem.getRateCount();

        //如果评分人数为0,说明它以前从未被评分过
        if ( rateCount == 0 )
        {
            goodsItem.setFavorablerate(Double.valueOf(order.getCommentRate()) / 5.0);
            goodsItem.setRateCount(goodsItem.getRateCount() + 1);
        }
        //若评分人数不为0
        else
        {
            Double newRate = ((Double.valueOf(order.getCommentRate()) / 5.0) + rateCount * oldRate) / (rateCount + 1);
            goodsItem.setFavorablerate(newRate);
            goodsItem.setRateCount(goodsItem.getRateCount() + 1);
        }
        //保存评分信息
        goodsItemRepository.save(goodsItem);

        return ResultUtils.success(oldOrder);
    }



    //得到商家最近5年,每年的销售额,只有订单状态为"已完成"才会被记录
    //必传: 商户ID
    public Result getFiveYearsIncome(Integer merchantId)
    {
        //MERCHANT_NOT_EXIST(6,"商家不存在或者已注销！")
        if ( merchantRepository.findMerchantByMerchantId(merchantId) == null )
            return ResultUtils.error(MsgId.MERCHANT_NOT_EXIST);

        //设置Date对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String End_2021 = "2021-12-31 23:59:59";
        String Begin_2021 = "2021-01-01 00:00:00";
        String End_2020 = "2020-12-31 23:59:59";
        String Begin_2020 = "2020-01-01 00:00:00";
        String End_2019 = "2019-12-31 23:59:59";
        String Begin_2019 = "2019-01-01 00:00:00";
        String End_2018 = "2018-12-31 23:59:59";
        String Begin_2018 = "2018-01-01 00:00:00";
        String End_2017 = "2017-12-31 23:59:59";
        String Begin_2017 = "2017-01-01 00:00:00";


        Double Income_2021 = 0.0, Income_2020 = 0.0, Income_2019 = 0.0, Income_2018 = 0.0, Income_2017 = 0.0;

        List<Double> fiveYearsIncomeList = new ArrayList<>();

        List<Order> orderList = orderRepository.findAllByMerchantId(merchantId);

        try
        {
            for ( Order order : orderList )
            {
                Date orderTime = order.getOrderDate();
                if ( order.getOrderStatus().equals("已完成") )
                {
                    if ( orderTime.before(sdf.parse(End_2021)) && orderTime.after(sdf.parse(Begin_2021)) )
                        Income_2021 += order.getTotalPrice();
                    else if ( orderTime.before(sdf.parse(End_2020)) && orderTime.after(sdf.parse(Begin_2020)) )
                        Income_2020 += order.getTotalPrice();
                    else if ( orderTime.before(sdf.parse(End_2019)) && orderTime.after(sdf.parse(Begin_2019)) )
                        Income_2019 += order.getTotalPrice();
                    else if ( orderTime.before(sdf.parse(End_2018)) && orderTime.after(sdf.parse(Begin_2018)) )
                        Income_2018 += order.getTotalPrice();
                    else if ( orderTime.before(sdf.parse(End_2017)) && orderTime.after(sdf.parse(Begin_2017)) )
                        Income_2017 += order.getTotalPrice();
                }
            }

            fiveYearsIncomeList.add(Income_2017);
            fiveYearsIncomeList.add(Income_2018);
            fiveYearsIncomeList.add(Income_2019);
            fiveYearsIncomeList.add(Income_2020);
            fiveYearsIncomeList.add(Income_2021);

            return ResultUtils.success(fiveYearsIncomeList);
        } catch ( ParseException e )
        {
            e.printStackTrace();
        }
        return ResultUtils.error(MsgId.DATE_READ_FAIL);
    }


    //得到2021年,12个月里的月收入
    //必传: 商户ID
    public Result getMonthIncomeThisYear(Integer merchantId)
    {
        //MERCHANT_NOT_EXIST(6,"商家不存在或者已注销！")
        if ( merchantRepository.findMerchantByMerchantId(merchantId) == null )
            return ResultUtils.error(MsgId.MERCHANT_NOT_EXIST);

        //设置Date对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String End_January = "2021-01-31 23:59:59";
        String Begin_January = "2021-01-01 00:00:00";
        String End_Febrary = "2021-02-28 23:59:59";
        String Begin_Febrary = "2021-02-01 00:00:00";
        String End_March = "2021-03-31 23:59:59";
        String Begin_March = "2021-03-01 00:00:00";
        String End_April = "2021-04-30 23:59:59";
        String Begin_April = "2021-04-01 00:00:00";
        String End_May = "2021-05-31 23:59:59";
        String Begin_May = "2021-05-01 00:00:00";
        String End_June = "2021-06-30 23:59:59";
        String Begin_June = "2021-06-01 00:00:00";
        String End_July = "2021-07-31 23:59:59";
        String Begin_July = "2021-07-01 00:00:00";
        String End_August = "2021-08-31 23:59:59";
        String Begin_August = "2021-08-01 00:00:00";
        String End_Sept = "2021-09-30 23:59:59";
        String Begin_Sept = "2021-09-01 00:00:00";
        String End_Oct = "2021-10-31 23:59:59";
        String Begin_Oct = "2021-10-01 00:00:00";
        String End_Nov = "2021-11-30 23:59:59";
        String Begin_Nov = "2021-11-01 00:00:00";
        String End_Dec = "2021-12-31 23:59:59";
        String Begin_Dec = "2021-12-01 00:00:00";

        Double Jan_Income = 0.0, Feb_Income = 0.0, Mar_Income = 0.0, Apr_Income = 0.0, May_Income = 0.0,
                Jun_Income = 0.0, Jul_Income = 0.0, Aug_Income = 0.0, Sep_Income = 0.0, Oct_Income = 0.0,
                Nov_Income = 0.0, Dec_Income = 0.0;

        List<Double> monthIncomeList = new ArrayList<>();

        List<Order> orderList = orderRepository.findAllByMerchantId(merchantId);

        try
        {
            for ( Order order : orderList )
            {
                Date orderTime = order.getOrderDate();
                if ( order.getOrderStatus().equals("已完成") )
                {
                    if ( orderTime.before(sdf.parse(End_January)) && orderTime.after(sdf.parse(Begin_January)) )
                        Jan_Income += order.getTotalPrice();
                    else if ( orderTime.before(sdf.parse(End_Febrary)) && orderTime.after(sdf.parse(Begin_Febrary)) )
                        Feb_Income += order.getTotalPrice();
                    else if ( orderTime.before(sdf.parse(End_March)) && orderTime.after(sdf.parse(Begin_March)) )
                        Mar_Income += order.getTotalPrice();
                    else if ( orderTime.before(sdf.parse(End_April)) && orderTime.after(sdf.parse(Begin_April)) )
                        Apr_Income += order.getTotalPrice();
                    else if ( orderTime.before(sdf.parse(End_May)) && orderTime.after(sdf.parse(Begin_May)) )
                        May_Income += order.getTotalPrice();
                    else if ( orderTime.before(sdf.parse(End_June)) && orderTime.after(sdf.parse(Begin_June)) )
                        Jun_Income += order.getTotalPrice();
                    else if ( orderTime.before(sdf.parse(End_July)) && orderTime.after(sdf.parse(Begin_July)) )
                        Jul_Income += order.getTotalPrice();
                    else if ( orderTime.before(sdf.parse(End_August)) && orderTime.after(sdf.parse(Begin_August)) )
                        Aug_Income += order.getTotalPrice();
                    else if ( orderTime.before(sdf.parse(End_Sept)) && orderTime.after(sdf.parse(Begin_Sept)) )
                        Sep_Income += order.getTotalPrice();
                    else if ( orderTime.before(sdf.parse(End_Oct)) && orderTime.after(sdf.parse(Begin_Oct)) )
                        Oct_Income += order.getTotalPrice();
                    else if ( orderTime.before(sdf.parse(End_Nov)) && orderTime.after(sdf.parse(Begin_Nov)) )
                        Nov_Income += order.getTotalPrice();
                    else if ( orderTime.before(sdf.parse(End_Dec)) && orderTime.after(sdf.parse(Begin_Dec)) )
                        Dec_Income += order.getTotalPrice();
                }
            }

            monthIncomeList.add(Jan_Income);
            monthIncomeList.add(Feb_Income);
            monthIncomeList.add(Mar_Income);
            monthIncomeList.add(Apr_Income);
            monthIncomeList.add(May_Income);
            monthIncomeList.add(Jun_Income);
            monthIncomeList.add(Jul_Income);
            monthIncomeList.add(Aug_Income);
            monthIncomeList.add(Sep_Income);
            monthIncomeList.add(Oct_Income);
            monthIncomeList.add(Nov_Income);
            monthIncomeList.add(Dec_Income);

            return ResultUtils.success(monthIncomeList);
        } catch ( ParseException e )
        {
            e.printStackTrace();
        }

        return ResultUtils.error(MsgId.DATE_READ_FAIL);
    }

    public Result getDailyAverageIncome(Integer merchantId)
    {
        List<Double> dailyAverageIncomeList = ( List<Double> ) getMonthIncomeThisYear(merchantId).getData();

        double element = 0.0;
        for(int i = 1; i <= 12; i++)
        {
            //如果是1~7月
            if(i<=7)
            {
                //如果是偶数月
                if((i%2) == 0)
                {
                    //除了2月,都有30天
                    if(i != 2)
                    {
                        element = dailyAverageIncomeList.get(i-1) / 30;
                        dailyAverageIncomeList.set(i,element);
                    }
                    //2021年2月,有28天
                    else
                    {
                        element = dailyAverageIncomeList.get(i-1) / 28;
                        dailyAverageIncomeList.set(i-1,element);
                    }
                }
                //1~7月的奇数月,有31天
                else
                {
                    element = dailyAverageIncomeList.get(i-1) / 31;
                    dailyAverageIncomeList.set(i-1,element);
                }
            }
            //8~12月
            else
            {
                //偶数月有31天
                if((i%2) == 0)
                {
                    element = dailyAverageIncomeList.get(i-1) / 31;
                    dailyAverageIncomeList.set(i-1,element);
                }
                //奇数月有30天
                else
                {
                    element = dailyAverageIncomeList.get(i-1) / 30;
                    dailyAverageIncomeList.set(i-1,element);
                }
            }
        }
        return ResultUtils.success(dailyAverageIncomeList);
    }
}
