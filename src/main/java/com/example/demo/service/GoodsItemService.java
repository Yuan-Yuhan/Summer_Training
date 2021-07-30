package com.example.demo.service;


import com.example.demo.enm.MsgId;
import com.example.demo.entity.Goods;
import com.example.demo.entity.GoodsItem;
import com.example.demo.entity.Result;
import com.example.demo.entity.Sales;
import com.example.demo.repository.GoodsItemRepository;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.MerchantRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */

//商品类
@Service
public class GoodsItemService {
    @Autowired
    GoodsItemRepository goodsItemRepository;
    @Autowired
    MerchantRepository merchantRepository;
    @Autowired
    GoodsRepository goodsRepository;

    /**
     *添加一个商品
     * 必传项gname,transportfee,merchantId,details,type和商品图片文件
     */

    public Result addOne(String gname, Double transportfee, Integer merchantId,
                         String details, String type){

        GoodsItem goodsItem = new GoodsItem();
        goodsItem.setGname(gname);
        goodsItem.setTransportfee(transportfee);
        goodsItem.setMerchantId(merchantId);
        goodsItem.setDetails(details);
        goodsItem.setType(type);

        goodsItem.setToken(1);

        goodsItemRepository.save(goodsItem);
        return ResultUtils.success(goodsItem);
    }
    /**
     *更新商品
     * 必传sid,必传gname,transportfee,details,type,商品图片文件
     */
    public Result update(GoodsItem goodsItem){
        if (goodsItem.getSid()==null){
            return ResultUtils.error(MsgId.NO_ID);
        }
        GoodsItem old=goodsItemRepository.findBySid(goodsItem.getSid());
        old.setGname(goodsItem.getGname());

        old.setTransportfee(goodsItem.getTransportfee());


        old.setDetails(goodsItem.getDetails());


        old.setType(goodsItem.getType());

        old.setSpic(goodsItem.getSpic());

        goodsItemRepository.save(old);
        return ResultUtils.success();
    }
    /**
     *根据商品id查找单个商品
     */
    public Result findOneById(Integer sid){
        GoodsItem goodsItem=goodsItemRepository.findBySid(sid);
        if(goodsItem==null){
            return ResultUtils.error(MsgId.GOODS_NOT_EXIST);
        }
        return ResultUtils.success(goodsItem);

    }
    /**
     * 根据产品名称模糊查询，匹配后放回所有合适的数据
     */
    public Result findAllByGnameLike(String name)
    {
        return ResultUtils.success(goodsItemRepository.findAllByGnameLike("%"+name+"%"));
    }

    public Result findAllByGnameOfThisMerchant(String name, Integer merchantId)
    {
        List<GoodsItem> goodsItemList = goodsItemRepository.findAllByGnameLike(name);

        List<GoodsItem> thisMerchantGoodsItemList = new ArrayList<>();

        for(GoodsItem goodsItem: goodsItemList)
        {
            if( goodsItem.getMerchantId().equals(merchantId) )
                thisMerchantGoodsItemList.add(goodsItem);
        }

        return ResultUtils.success(thisMerchantGoodsItemList);
    }


    //根据产品名称模糊查询,然后按销量降序排列
    public Result find_And_SortBySales(String name)
    {
        //得到GoodItem的List
        List<GoodsItem> goodsItemList = goodsItemRepository.findAllByGnameLike("%"+name+"%");
        //排序
        goodsItemList.sort(Comparator.comparing(GoodsItem::getSales));
        Collections.reverse(goodsItemList);


        return ResultUtils.success(goodsItemList);

    }
    //根据产品名称模糊查询,然后按好评率降序排列
    public Result find_And_SortByFavorRate(String name)
    {
        //得到GoodItem的List
        List<GoodsItem> goodsItemList = goodsItemRepository.findAllByGnameLike("%"+name+"%");
        //排序
        goodsItemList.sort(Comparator.comparing(GoodsItem::getFavorablerate));
        Collections.reverse(goodsItemList);


        return ResultUtils.success(goodsItemList);
    }


    //得到每个商品的销量,salesList中的元素: gname商品名, sales 销量
    public Result getSalesOfGoodsItem(Integer merchantId)
    {
        if(merchantRepository.findMerchantByMerchantId(merchantId) == null)
            return ResultUtils.error(MsgId.MERCHANT_NOT_EXIST);

        List<GoodsItem> goodsItemList = goodsItemRepository.findAllByMerchantId(merchantId);

        List<Sales> salesList = new ArrayList<>();

        Sales sales = new Sales();

        for(GoodsItem goodsItem: goodsItemList)
        {
            sales.setGname(goodsItem.getGname());
            sales.setSales(goodsItem.getSales());
            salesList.add(sales);
        }

        salesList.sort(Comparator.comparing(Sales::getSales));
        Collections.reverse(salesList);
        //未经过排序
        return ResultUtils.success(salesList);
    }

    //通过MerchantID获取GoodsList
    public Result getGoodListByMerchantId(Integer merchantId)
    {
        //通过MerchantID找到所有的商品List
        List<GoodsItem> goodsItemList = goodsItemRepository.findAllByMerchantId(merchantId);
        //建立商品ID的list
        List<Integer> sidList = new ArrayList<>();
        //把商品List的商品ID信息存入sidList
        for(GoodsItem goodsItem: goodsItemList)
        {
            sidList.add(goodsItem.getSid());
        }
        //新建一存放规格信息的goodsList
        List<Goods> goodsList = new ArrayList<>();
        for(Integer sid: sidList)
        {
            goodsList.addAll(goodsRepository.findAllBySid(sid));
        }

        //返回这个商户所有的规格list
        return ResultUtils.success(goodsList);
    }


}
