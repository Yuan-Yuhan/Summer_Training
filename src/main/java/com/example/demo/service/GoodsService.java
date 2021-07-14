package com.example.demo.service;


import com.example.demo.enm.MsgId;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Result;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author 赵思阳
 * @since 2021/7/12
 * @version 1.0
 */
@Service
public class GoodsService {

    @Autowired
    GoodsRepository goodsRepository;
    /**
     *设置商品规格
     */
    //必传sid、gpic、specification、originalPrice、discount、inventory
    public Result addOne(Goods goods){
        if(goods.getSid()==null){
            return ResultUtils.error(MsgId.NO_ID);
        }else if(goods.getGpic()==null||goods.getSpecification()==null||goods.getOriginalPrice()<0||goods.getDiscount()<0||goods.getInventory()<0){
            return ResultUtils.error(MsgId.GOODS_NOT_DETAILED);
        }
        goods.setPrice(goods.getOriginalPrice()*goods.getDiscount());
        goodsRepository.save(goods);
        return ResultUtils.success(goods);
    }
    /**
     *更新商品规格
     */
    public Result update(Goods goods){
        if(goods.getGid()==null){
            return ResultUtils.error(MsgId.NO_ID);
        }
        goods.setPrice(goods.getOriginalPrice()*goods.getDiscount());
        goodsRepository.save(goods);
        return ResultUtils.success(goods);
    }

    /**
     *查找一个商品的所有规格
     */
    public Result findAllBySid(int sid){
        return ResultUtils.success(goodsRepository.findAllBySid(sid));
    }
    /**
     *查找一个商品的一个规格
     */
    public Result findGoodsByGid(int gid){
        Goods goods=goodsRepository.findGoodsByGid(gid);
        if(goods==null){
            return ResultUtils.error(MsgId.GOODS_NOT_EXIST);
        }
        return ResultUtils.success(goodsRepository.findGoodsByGid(gid));
    }


}
