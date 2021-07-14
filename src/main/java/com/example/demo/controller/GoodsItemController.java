package com.example.demo.controller;

import com.example.demo.entity.GoodsItem;
import com.example.demo.entity.Result;
import com.example.demo.service.GoodsItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author 赵思阳
 * @since 2021/7/13
 * @version 1.0
 */
@RestController
@RequestMapping ("/goodsItem")
public class GoodsItemController {

    @Autowired
    GoodsItemService goodsItemService;

    /**
     * 添加商品
     * @param goodsItem
     * @return
     */
    @RequestMapping("/addOne")
    public Result addGoodsItem(GoodsItem goodsItem){
        return goodsItemService.addOne(goodsItem);
    }

    /**
     * 模糊查询，搜索商品
     * @param name
     * @return
     */
    @RequestMapping("/findAllByGnameLike")
    public Result search(String name){
        return goodsItemService.findAllByGnameLike((name));
    }

}
