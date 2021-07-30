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
//运送地址
@Service
public class DeliveryAddressService {
    @Autowired
    DeliveryAddressRepository repository;
    /**
     *用户添加收货地址
     * 必传uid,acceptPerson,acceptPhone,acceptPlace,detailedAddress
     */
    public Result addAddress(DeliveryAddress address)
    {
        if ( address.getUid() == null )
        {
            return ResultUtils.error(MsgId.DELIVERY_Lack_UID);
        } else if ( address.getAcceptPerson() == null || address.getAcceptPhone() == null || address.getAcceptPlace() == null || address.getDetailedAddress() == null )
        {
            return ResultUtils.error(MsgId.DELIVERY_Lack_AddressInfo);
        }

        //原来没有收获地址，直接保存
        if ( repository.findDeliveryAddressByUid(address.getUid()) == null )
        {
            repository.save(address);

            return ResultUtils.success(address);
        } else
        {//有收货地址的话，更新收货地址
            DeliveryAddress oldAddress = repository.findDeliveryAddressByUid(address.getUid());
            oldAddress.setAcceptPerson(address.getAcceptPerson());
            oldAddress.setAcceptPhone(address.getAcceptPhone());
            oldAddress.setAcceptPlace(address.getAcceptPlace());
            oldAddress.setDetailedAddress(address.getDetailedAddress());
            repository.save(oldAddress);

            return ResultUtils.success(oldAddress);
        }
    }
        /**
         *用户修改收货地址
         *必传deliverId,uid,acceptPerson,acceptPhone,acceptPlace,detailedAddress
         */
    public Result updateAddress(DeliveryAddress address){
        if(address.getDeliverId()==null){
            return ResultUtils.error(MsgId.DELIVERY_Lack_deliveryID);
        }
        else if(address.getUid()==null){
            return ResultUtils.error(MsgId.DELIVERY_Lack_UID);
        }else if(address.getAcceptPerson()==null||address.getAcceptPhone()==null||address.getAcceptPlace()==null||address.getDetailedAddress()==null){
            return ResultUtils.error(MsgId.DELIVERY_Lack_AddressInfo);
        }
        repository.save(address);
        return ResultUtils.success(address);
    }
}
