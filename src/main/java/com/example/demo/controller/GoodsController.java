package com.example.demo.controller;

import com.example.demo.entity.Goods;
import com.example.demo.entity.Result;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author 赵思阳
 * @since 2021/7/13
 * @version 1.0
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    /**
     * 添加商品规格
     * @param goods
     * @return
     */
    @RequestMapping("/addOne")
    public Result addGoods(Goods goods){
        return goodsService.addOne(goods);
    }


}
