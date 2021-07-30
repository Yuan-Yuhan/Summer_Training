package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */
//商品类型
@RestController
@RequestMapping("/type")
public class TypeController
{
    @Autowired
    TypeService typeService;

    @RequestMapping("/getAll")
    public Result findAll(Integer merchantId)
    {
        return typeService.findAllByMerchantId(merchantId);
    }

    @RequestMapping("/add")
    public Result addType(String firstType, String secondType, Integer merchantId)
    {
        return typeService.addType(firstType, secondType, merchantId);
    }

    @RequestMapping("/edit")
    public Result editType(Integer typeId, String firstType, String secondType)
    {
        return typeService.editType(typeId, firstType, secondType);
    }

    @RequestMapping("/delete")
    public Result delete(Integer typeId)
    {
        return typeService.deleteType(typeId);
    }
}
