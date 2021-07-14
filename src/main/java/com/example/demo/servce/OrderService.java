package com.example.demo.servce;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Order;
import com.example.demo.entity.Result;
import com.example.demo.repository.OrderRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService
{
    @Autowired
    OrderRepository orderRepository;

    /**
     *新增订单
     */
    //必传:用户ID,商品ID,规格ID,商户ID,商品数量
    public Result addOne(Order order){
        if(order.getUid() == null || order.getSid() == null
        || order.getGid() == null || order.getMerchantId() == null
        || order.getGoodsCount() == null)
        {
            return ResultUtils.error(MsgId.ORDER_Lack_Paraments);
        }

        order.setOrderStatus("未支付");

        orderRepository.save(order);

        return ResultUtils.success();
    }

}
