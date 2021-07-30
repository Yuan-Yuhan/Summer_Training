package com.example.demo.service;


import com.example.demo.enm.MsgId;
import com.example.demo.entity.Goods;
import com.example.demo.entity.GoodsItem;
import com.example.demo.entity.Result;
import com.example.demo.repository.GoodsItemRepository;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author 赵思阳
 * @since 2021-07-23
 * @version 1.0
 */
//商品规格类
@Service
public class GoodsService {

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    GoodsItemRepository goodsItemRepository;
    /**
     *设置商品规格
     */
    //必传sid(商品id)、specification、originalPrice、discount、inventory
    // 图片文件可选
    //不传规格id——gid
    public Result addOne(Goods goods){
        if(goods.getSid()==null){
            return ResultUtils.error(MsgId.NO_ID);
        }else if(goods.getSpecification()==null||goods.getOriginalPrice()==null||goods.getDiscount()==null||goods.getInventory()==null){
            return ResultUtils.error(MsgId.GOODS_NOT_DETAILED);
        }
        goods.setPrice(goods.getOriginalPrice()*goods.getDiscount());
        GoodsItem goodsItem=goodsItemRepository.findBySid(
                goods.getSid());
        if(goodsItem.getLowestprice()==null|| goods.getPrice()<goodsItem.getLowestprice()){
            goodsItem.setLowestprice(goods.getPrice());
            goodsItemRepository.save(goodsItem);
        }
        goodsRepository.save(goods);
        return ResultUtils.success();
    }
    /**
     *更新商品规格
     * 必传gid，specification，originalPrice,discount,inventory
     */
    public Result update(Goods goods){
        if(goods.getGid()==null){
            return ResultUtils.error(MsgId.NO_ID);
        }
        Goods old=goodsRepository.findGoodsByGid(goods.getGid());
        goods.setPrice(goods.getOriginalPrice()*goods.getDiscount());
        GoodsItem goodsItem=goodsItemRepository.findBySid(
                old.getSid());
        if(goodsItem.getLowestprice()==null|| goods.getPrice()<goodsItem.getLowestprice())
        {
            goodsItem.setLowestprice(goods.getPrice());
            goodsItemRepository.save(goodsItem);
        }
        old.setSpecification(goods.getSpecification());
        old.setOriginalPrice(goods.getOriginalPrice());
        old.setDiscount(goods.getDiscount());
        old.setInventory(goods.getInventory());
        old.setGpic(goods.getGpic());
        old.setPrice(goods.getOriginalPrice()*goods.getDiscount());
        goodsRepository.save(old);
        return ResultUtils.success();
    }

    /**
     *查找一个商品的所有规格
     */
    public Result findAllBySid(int sid){
        return ResultUtils.success(goodsRepository.findAllBySid(sid));
    }
    /**
     *查找一个商品的所有规格
     */
    public Result findGoodsByGid(Integer gid){
        Goods goods=goodsRepository.findGoodsByGid(gid);
        if(goods==null){
            return ResultUtils.error(MsgId.GOODS_NOT_EXIST);
        }
        return ResultUtils.success(goodsRepository.findGoodsByGid(gid));
    }


}
