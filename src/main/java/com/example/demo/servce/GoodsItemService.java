package com.example.demo.servce;


import com.example.demo.enm.MsgId;
import com.example.demo.entity.Goods;
import com.example.demo.entity.GoodsItem;
import com.example.demo.entity.Result;
import com.example.demo.repository.GoodsItemRepository;

import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsItemService {
    @Autowired
    GoodsItemRepository goodsItemRepository;

    /**
     *添加一个商品
     */
    //必传项商品名、类型、展示图、折扣、运费、详情
    public Result addOne(GoodsItem goods){
        goodsItemRepository.save(goods);
        return ResultUtils.success();
    }
    /**
     *更新商品
     */
    public Result update(GoodsItem goodsItem){
        if (goodsItem.getSid()==null){
            return ResultUtils.error(MsgId.NO_ID);
        }
        goodsItemRepository.save(goodsItem);
        return ResultUtils.success();
    }
    /**
     *根据商品id查找单个商品
     */
    public Result findOneById(int sid){
        GoodsItem goodsItem=goodsItemRepository.findBySid(sid);
        if(goodsItem==null){
            return ResultUtils.error(MsgId.GOODS_NOT_EXIST);
        }
        return ResultUtils.success(goodsItem);

    }
    /**
     * 根据产品名称模糊查询，匹配后放回所有合适的数据
     */
    public Result findAllByGnameLike(String name){
        return ResultUtils.success(goodsItemRepository.findAllByGnameLike("%"+name+"%"));
    }

//    public Result findFirst6ByOrderBySidAsc(int sid)
//    {
//        return ResultUtils.success(goodsItemRepository.findFirst6ByOrderBySidAsc(sid));
//    }

}
