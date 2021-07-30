package com.example.demo.controller;

import com.example.demo.entity.DeliveryAddress;
import com.example.demo.entity.Result;
import com.example.demo.repository.DeliveryAddressRepository;
import com.example.demo.service.DeliveryAddressService;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 赵思阳
 * @Date 2021/7/14 14:11
 * @Version 1.0
 */

//运送地址
@RestController
@RequestMapping("/address")
public class DeliveryAddressController {
    @Autowired
    DeliveryAddressService deliveryAddressService;

    @Autowired
    DeliveryAddressRepository daRepository;
    /**
     * 添加收货地址,仅可以使用一次
     * @param address
     * @return
     */
    @RequestMapping("/addOne")
    public Result addAddress(DeliveryAddress address){
        return deliveryAddressService.addAddress(address);
    }
    /**
     * 修改收货地址
     * @param address
     * @return
     */
    @RequestMapping("/update")
    public Result updateAddress(DeliveryAddress address){
        return deliveryAddressService.updateAddress(address);
    }

    //获取收货地址
    @RequestMapping("/getAddress")
    public Result getAddress(Integer uid)
    {
        return ResultUtils.success(daRepository.findDeliveryAddressByUid(uid));
    }

}
