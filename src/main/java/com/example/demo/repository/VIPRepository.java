package com.example.demo.repository;

import com.example.demo.entity.VIP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author 赵思阳
 * @Date 2021/7/14 10:28
 * @Version 1.0
 */
public interface VIPRepository extends JpaRepository<VIP,Integer>
{
    List<VIP> findAllByMerchantId(Integer merchantId);
    VIP findVIPByVipId(Integer vipId);

    VIP findByUidAndMerchantId(Integer uid, Integer merchantId);
}
