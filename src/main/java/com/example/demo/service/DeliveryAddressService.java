package com.example.demo.service;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.DeliveryAddress;
import com.example.demo.entity.Result;
import com.example.demo.repository.DeliveryAddressRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 赵思阳
 * @Date 2021/7/14 13:49
 * @Version 1.0
 */
@Service
public class DeliveryAddressService {
    @Autowired
    DeliveryAddressRepository repository;
    /**
     *用户添加收货地址
     * 必传uid,acceptPerson,acceptPhone,acceptPlace,detailedAddress
     */
    public Result addAddress(DeliveryAddress address){
        if(address.getuId()==null){
            return ResultUtils.error(MsgId.NO_ID);
        }else if(address.getAcceptPerson()==null||address.getAcceptPhone()==null||address.getAcceptPlace()==null||address.getDetailedAddress()==null){
            return ResultUtils.error(MsgId.GOODS_NOT_DETAILED);
        }
        repository.save(address);
        return ResultUtils.success(address);

    }
    /**
     *用户修改收货地址
     * 必传deliverId
     */
    public Result updateAddress(DeliveryAddress address){
        if(address.getDeliverId()==null){
            return ResultUtils.error(MsgId.NO_ID);
        }
        repository.save(address);
        return ResultUtils.success(address);
    }
}
