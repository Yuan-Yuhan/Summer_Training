package com.example.demo.servce;


import com.example.demo.enm.MsgId;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Result;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {

    @Autowired
    GoodsRepository goodsRepository;
    /**
     *设置商品规格
     */
    //必传商品id、规格、规格图、价格、库存
    public Result addOne(Goods goods){
        if(goods.getSid()==null){
            return ResultUtils.error(MsgId.NO_ID);
        }
        goodsRepository.save(goods);
        return ResultUtils.success();
    }
    /**
     *更新商品规格
     */
    public Result update(Goods goods){
        if(goods.getGid()==null){
            return ResultUtils.error(MsgId.NO_ID);
        }
        goodsRepository.save(goods);
        return ResultUtils.success();
    }

    /**
     *查找一个商品的所有规格
     */
    public Result findAllBySid(int sid)
    {
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
