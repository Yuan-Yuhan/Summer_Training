package com.example.demo.repository;

import com.example.demo.entity.InventoryLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */
public interface InventoryLogRepository extends JpaRepository<InventoryLog, Integer>
{
    List<InventoryLog> findAllByMerchantId(Integer merchantId);
}
