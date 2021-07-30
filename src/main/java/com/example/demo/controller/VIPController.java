package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.entity.VIP;
import com.example.demo.service.VIPService;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 赵思阳
 * @Date 2021/7/14 10:33
 * @Version 1.0
 */

//会员
@RestController
@RequestMapping("/vip")
public class VIPController {
    @Autowired
    VIPService vipService;

    @RequestMapping("/becomeVIP")
    public Result becomeVIP(Integer uid, Integer merchantId)
    {
        return vipService.becomeVIP(uid, merchantId);
    }

    @RequestMapping("/changeLevelAndDiscount")
    public Result change(VIP vip)
    {
        return vipService.changeLevelAndDiscount(vip);
    }

    //取消用户的会员资格
    @RequestMapping("/disqualify")
    public Result disqualify(VIP vip)
    {
        return vipService.disqualify(vip);
    }

    //获取本店铺的所有会员的信息
    @RequestMapping("/getAllVIPs")
    public Result getAllvipInfo(Integer merchantId)
    {
        return ResultUtils.success(vipService.getAllvipInfo(merchantId));
    }
}
