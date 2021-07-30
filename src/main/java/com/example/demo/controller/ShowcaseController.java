package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.entity.Showcase;
import com.example.demo.repository.ShowcaseRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */

//用于商铺首页的商品展示
@RestController
@RequestMapping("/showcase")
public class ShowcaseController
{
    @Autowired
    ShowcaseRepository showcaseRepository;

    @RequestMapping("/showMe")
    public Result showMe(Integer merchantId)
    {
        Showcase showcase = showcaseRepository.findShowcaseByMerchantId(merchantId);

        return ResultUtils.success(showcase);
    }

    @RequestMapping("/update")
    public Result update(Integer merchantId, Integer sid1, Integer sid2, Integer sid3)
    {
        Showcase showcase = showcaseRepository.findShowcaseByMerchantId(merchantId);
        showcase.setSidOne(sid1);
        showcase.setSidTwo(sid2);
        showcase.setSidThree(sid3);

        showcaseRepository.save(showcase);

        return ResultUtils.success();
    }
}
