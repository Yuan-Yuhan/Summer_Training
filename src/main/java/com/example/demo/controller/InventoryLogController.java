package com.example.demo.controller;

/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */
import com.example.demo.entity.Result;
import com.example.demo.repository.InventoryLogRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;

//商品的出入库日志
@RestController
@RequestMapping("/log")
public class InventoryLogController
{
    @Autowired
    InventoryLogRepository inventoryLogRepository;

    @RequestMapping("/askAll")
    public Result askAll(Integer merchantId)
    {
        return ResultUtils.success(inventoryLogRepository.findAllByMerchantId(merchantId));
    }
}
