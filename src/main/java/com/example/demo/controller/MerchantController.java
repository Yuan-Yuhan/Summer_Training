package com.example.demo.controller;

import com.example.demo.entity.Merchant;
import com.example.demo.entity.Result;
import com.example.demo.servce.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 赵思阳
 * @Date 2021/7/13 16:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    MerchantService merchantService;
    /**
     *商户登录
     * @param phone,password
     * @return
     */
    @RequestMapping("/login")
    public Result login(String phone,String password){
        return merchantService.login(phone,password);

    }
    /**
     *商户注册
     * @param phone,password
     * @return
     */
    public Result register(String phone,String name,String password){
        return merchantService.register(phone,name,password);
    }
    /**
     * 商户信息更新 id必传
     * @param merchant
     * @return
     */
    @RequestMapping("/update")
    public Result update(Merchant merchant){
        return merchantService.update(merchant);
    }

    /**
     * 商户删除，id必传
     * @param merchantId
     * @return
     */
    @RequestMapping("/delete" )
    public Result delete(Integer merchantId){
        return merchantService.delete(merchantId);
    }

}
