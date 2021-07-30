package com.example.demo.service;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.Result;
import com.example.demo.entity.Type;
import com.example.demo.repository.TypeRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */

//商品类型
@Service
public class TypeService
{
    @Autowired
    TypeRepository typeRepository;

    public Result findAllByMerchantId(Integer merchantId)
    {
        List<Type> typeList = typeRepository.findAllByMerchantId(merchantId);
        return ResultUtils.success(typeList);
    }

    public Result addType(String firstType, String secondType, Integer merchantId)
    {
        //后端确定merchantId不能为空
        if(merchantId == null)
        {
            return ResultUtils.error(MsgId.TYPE_NO_MerchantID);
        }
        //存储新type
        Type type = new Type();
        type.setFirstType(firstType);
        type.setSecondType(secondType);
        type.setMerchantId(merchantId);

        typeRepository.save(type);

        //重新刷新Type列表
        return ResultUtils.success(findAllByMerchantId(merchantId));

    }

    public Result editType(Integer typeId, String firstType, String secondType)
    {
        if(typeId == null)
            return ResultUtils.error(MsgId.TYPE_NO_TypeID);

        //通过TypeID找到Type对象
        Type type = typeRepository.findTypeByTypeId(typeId);

        type.setFirstType(firstType);
        type.setSecondType(secondType);

        //存储
        typeRepository.save(type);
        //重新刷新Type列表
        return ResultUtils.success(findAllByMerchantId(type.getMerchantId()));
    }

    public Result deleteType(Integer typeId)
    {
        if(typeId == null)
            return ResultUtils.error(MsgId.TYPE_NO_TypeID);

        Type type = typeRepository.findTypeByTypeId(typeId);

        Integer merchantId = type.getMerchantId();

        typeRepository.delete(type);

        //重新刷新Type列表
        return ResultUtils.success(findAllByMerchantId(merchantId));
    }
}