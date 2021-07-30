package com.example.demo.repository;

import com.example.demo.entity.GoodsItem;
import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 袁宇涵
 * @since 2021/7/14
 * @version 1.0
 */
public interface OrderRepository extends JpaRepository<Order,Integer>
{
    Order findByOrderId(Integer orderId);

    List<Order> findAllByUid(Integer uid);

    List<Order> findAllByMerchantId(Integer merchantId);

    List<Order> findAllBySidAndOrderStatus(Integer sid, String orderStatus);


}
