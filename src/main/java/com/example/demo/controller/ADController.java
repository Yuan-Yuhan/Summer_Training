package com.example.demo.controller;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */


import com.example.demo.enm.MsgId;
import com.example.demo.entity.AD;
import com.example.demo.entity.Result;
import com.example.demo.repository.ADRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//广告、文章，轮播图类
@RestController
@RequestMapping("/ad")
public class ADController
{
    @Autowired
    ADRepository adRepository;

    //访问商家设置的轮播图
    @RequestMapping("/ask")
    public Result askURL(Integer merchantId)
    {
        AD ad = adRepository.findADByMerchantId(merchantId);
        if(ad == null)
        {
            AD newAD = new AD();
            newAD.setMerchantId(merchantId);
            adRepository.save(newAD);
            return ResultUtils.success(newAD);
        }
        return ResultUtils.success(ad);
    }

    @RequestMapping("/update")
    public Result update(Integer merchantId, String picUrl1, String webUrl1,
                         String picUrl2, String webUrl2, String picUrl3, String webUrl3)
    {
        AD ad = adRepository.findADByMerchantId(merchantId);
        ad.setPicUrlOne(picUrl1);
        ad.setPicUrlTwo(picUrl2);
        ad.setPicUrlThree(picUrl3);
        ad.setWebUrlOne(webUrl1);
        ad.setWebUrlTwo(webUrl2);
        ad.setWebUrlThree(webUrl3);
        adRepository.save(ad);

        return ResultUtils.success();
    }

}
