package com.example.demo.service;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import com.example.demo.entity.VIP;
import com.example.demo.repository.MerchantRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VIPRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 赵思阳
 * @Date 2021/7/14 10:31
 * @Version 1.0
 */

//会员类
@Service
public class VIPService {
    @Autowired
    VIPRepository vipRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MerchantRepository merchantRepository;

    public Result becomeVIP(Integer uid, Integer merchantId)
    {
        if(userRepository.findByUid(uid) == null)
            return ResultUtils.error(MsgId.VIP_Lack_UID);
        else if(merchantRepository.findMerchantByMerchantId(merchantId) == null)
            return ResultUtils.error(MsgId.VIP_Lack_MerchantID);

        if(vipRepository.findByUidAndMerchantId(uid, merchantId) != null)
        {
            return ResultUtils.error(MsgId.VIP_ALREADY_EXIST);
        }
        else
        {
            VIP vip = new VIP();
            vip.setMerchantId(merchantId);
            vip.setUid(uid);

            vip.setVipDiscount(1.0);
            vip.setVipLevel(1);

            vipRepository.save(vip);

            return ResultUtils.success(vip);
        }
    }

    //改变等级和折扣
    //必传: vipId, vipLevel,vipDiscount
    public Result changeLevelAndDiscount(VIP vip)
    {
        if(vip.getVipLevel() == null || vip.getVipLevel() < 1 || vip.getVipLevel() > 10)
            return ResultUtils.error(MsgId.VIP_WRONG_VIP_LEVEL);

        if(vip.getVipDiscount() == null || vip.getVipDiscount() <= 0.0 || vip.getVipDiscount() > 1.0)
            return ResultUtils.error(MsgId.VIP_WRONG_VIP_DISCOUNT);

        VIP old = vipRepository.findVIPByVipId(vip.getvVid());
        if(old == null)
            return ResultUtils.error(MsgId.VIP_Lack_VIPID);

        old.setVipLevel(vip.getVipLevel());
        old.setVipDiscount(vip.getVipDiscount());

        vipRepository.save(old);

        return ResultUtils.success();
    }

    public Result disqualify(VIP vip)
    {
        if(vip.getvVid() == null)
            return ResultUtils.error(MsgId.VIP_Lack_VIPID);

        vipRepository.delete(vip);
        return ResultUtils.success();
    }

    //得到店铺所有VIP的信息
    public Result getAllvipInfo(Integer merchantId)
    {
        List<VIP> vipList = vipRepository.findAllByMerchantId(merchantId);

        List<User> userList = new ArrayList<>();

        for(VIP vip:vipList)
        {
            userList.add(userRepository.findByUid(vip.getUid()));
        }
        return ResultUtils.success(userList);
    }
}
