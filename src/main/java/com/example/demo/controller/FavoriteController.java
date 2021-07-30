package com.example.demo.controller;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.Favorite;
import com.example.demo.entity.Result;
import com.example.demo.repository.FavoriteRepository;
import com.example.demo.service.FavoriteService;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */

//收藏商品功能
@RestController
@RequestMapping("/favorite")
public class FavoriteController
{
    @Autowired
    FavoriteService fService;

    @Autowired
    FavoriteRepository fRepository;

    @RequestMapping("/add")
    public Result add(Integer uid, Integer sid)
    {
        return fService.addFavorite(uid, sid);
    }

    @RequestMapping("/delete")
    public Result delete(Integer favoriteId)
    {
        return fService.deleteFavorite(favoriteId);
    }

    //根据UID,返回收藏的商品ID的列表
    @RequestMapping("/find")
    public Result find(Integer uid)
    {
        List<Favorite> favorites = fRepository.findAllByUid(uid);

        //这个用户还没有收藏任何商品
        if(favorites == null)
            return ResultUtils.error(MsgId.FAVORITE_NO_DATA);

        List<Integer> sidList = new ArrayList<>();

        for(Favorite favorite:favorites)
        {
            sidList.add(favorite.getSid());
        }

        return ResultUtils.success(sidList);

    }

    @RequestMapping("/findByMerchantId")
    public Result findByMerchantId(Integer merchantId)
    {
        return ResultUtils.success(fService.findAllByMerchant(merchantId));
    }
}
