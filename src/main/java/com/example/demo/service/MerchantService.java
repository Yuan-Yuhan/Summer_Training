package com.example.demo.service;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.Merchant;
import com.example.demo.entity.Result;
import com.example.demo.repository.MerchantRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author 赵思阳
 * @Date 2021/7/13 16:33
 * @Version 1.0
 */
@Service
public class MerchantService {
    @Autowired
    MerchantRepository merchantRepository;

    /**
     *商户登录
     */
    public Result login(String phone, String password) {
        Merchant merchantExistPhone = merchantRepository.findMerchantByMerchantPhone(phone);

        if (merchantExistPhone != null) {
            String merchantExistPassword = merchantExistPhone.getMerchantPassword();
            if (merchantExistPassword.equals(password)) {
                return  ResultUtils.success(merchantExistPhone);
            } else {
                return ResultUtils.error(MsgId.USER_ERR_PASS);
            }
        } else {
            return ResultUtils.error(MsgId.USER_NOT_EXIST);
        }
    }
    /**
     *商户注册
     */
    public Result register(String phone, String merchantName, String password) {

        Merchant merchantExistPhone = merchantRepository.findMerchantByMerchantPhone(phone);
        Merchant merchantExistName=merchantRepository.findMerchantByMerchantName(merchantName);
        Merchant merchant=new Merchant();
        merchant.setMerchantPhone(phone);
        merchant.setMerchantName(merchantName);
        merchant.setMerchantPassword(password);
        if (merchantExistPhone != null) {
            return ResultUtils.error(MsgId.PHONE_ALREADY_EXIST);
        } else if (merchantExistName!=null){
            return ResultUtils.error(MsgId.USER_ALREADY_EXIST);
        }else{
            merchantRepository.save(merchant);
            return ResultUtils.success(merchant);
        }
    }
    /**
     *商户信息更新
     */

    public Result update(Merchant merchant){
        if(merchant.getMerchantId()==null){
            ResultUtils.error(MsgId.NO_ID);
        }
        merchantRepository.save(merchant);
        return ResultUtils.success(merchant);

    }
    /**
     * 删除商户
     */

    public Result delete(int merchantId){
        Merchant merchant=merchantRepository.findMerchantByMerchantId(merchantId);
        if(merchant==null){
            return ResultUtils.error(MsgId.USER_NOT_EXIST);
        }
        merchantRepository.delete(merchant);
        return ResultUtils.success();

    }

}
