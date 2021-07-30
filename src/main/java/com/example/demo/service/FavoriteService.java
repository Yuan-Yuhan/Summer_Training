package com.example.demo.service;

/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */
import com.example.demo.enm.MsgId;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//用户进行商品收藏
@Service
public class FavoriteService
{
    @Autowired
    FavoriteRepository favoriteRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GoodsItemRepository goodsItemRepository;
    @Autowired
    MerchantRepository merchantRepository;
    @Autowired
    StarsRepository starsRepository;

    //用户新增收藏
    //
    public Result addFavorite(Integer uid, Integer sid)
    {
        GoodsItem goodsItem = goodsItemRepository.findBySid(sid);
        if(userRepository.findByUid(uid) == null)
            return ResultUtils.error(MsgId.FAVORITE_WRONG_UID);
        else if(goodsItem == null)
            return ResultUtils.error(MsgId.FAVORITE_WRONG_SID);

        if(favoriteRepository.findByUidAndSid(uid, sid) != null)
        {
            return ResultUtils.error(MsgId.FAVORITE_ALREADY_EXIST);
        }
        else
        {
            Stars stars = starsRepository.findStarsByGname(goodsItem.getGname());
            if(stars == null)
            {
                stars = new Stars();
                stars.setGname(goodsItem.getGname());
                stars.setStarNum(1);
                stars.setMerchantId(goodsItem.getMerchantId());
            }
            else
            {
                stars.setStarNum(stars.getStarNum()+1);
            }
            starsRepository.save(stars);

            Favorite favorite = new Favorite();
            favorite.setSid(sid);
            favorite.setUid(uid);
            favorite.setGname(goodsItem.getGname());
            favorite.setMerchantId(goodsItem.getMerchantId());

            favoriteRepository.save(favorite);

            return ResultUtils.success(favorite);
        }
    }

    //用户删除收藏
    //
    public Result deleteFavorite(Integer favoriteId)
    {
        Favorite favorite = favoriteRepository.findFavoriteByFavoriteId(favoriteId);

        if(favorite == null)
            return ResultUtils.error(MsgId.FAVORITE_WRONG_FavoriteID);

        Stars stars = starsRepository.findStarsByGname(favorite.getGname());
        if(stars == null)
        {
            return ResultUtils.error(MsgId.SYNC_FAULT_Favorite_Stars);
        }
        else
        {
            stars.setStarNum(stars.getStarNum()-1);
        }
        starsRepository.save(stars);


        favoriteRepository.delete(favorite);

        return ResultUtils.success();
    }

    public Result findAllByMerchant(Integer merchantId)
    {
        if(merchantRepository.findMerchantByMerchantId(merchantId) == null)
            return ResultUtils.error(MsgId.MERCHANT_NOT_EXIST);

        List<Stars> starsList = starsRepository.findAllByMerchantId(merchantId);

        return ResultUtils.success(starsList);
    }


}
