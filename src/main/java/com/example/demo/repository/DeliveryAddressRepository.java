package com.example.demo.repository;

import com.example.demo.entity.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author 赵思阳
 * @Date 2021/7/14 13:48
 * @Version 1.0
 */
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress,Integer> {

    DeliveryAddress findDeliveryAddressByDeliverId(Integer deliverId);

    DeliveryAddress findDeliveryAddressByUid(Integer uid);
}
