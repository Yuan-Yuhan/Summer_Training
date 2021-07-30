package com.example.demo.service;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.*;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.GoodsItemRepository;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */

//购物车相关服务
@Service
public class CartService
{
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GoodsItemRepository goodsItemRepository;
    @Autowired
    GoodsRepository goodsRepository;

    //用户添加到购物车
    //必要参数: uid, sid, gid
    public Result addToCart(Integer uid, Integer sid, Integer gid)
    {
        User user  = userRepository.findByUid(uid);
        if(user == null)
            return ResultUtils.error(MsgId.USER_NOT_EXIST);
        GoodsItem goodsItem = goodsItemRepository.findBySid(sid);
        if(goodsItem == null)
            return ResultUtils.error(MsgId.SHANGPIN_NOT_EXIST);
        Goods goods = goodsRepository.findGoodsByGid(gid);
        if(goods == null)
            return ResultUtils.error(MsgId.GUIGE_NOT_EXIST);

        if(cartRepository.findByUidAndSidAndGid(uid, sid, gid) != null)
        {
            return ResultUtils.error(MsgId.Cart_ALREADY_EXIST);
        }
        else
        {
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setSid(sid);
            cart.setGid(gid);
            cart.setGname(goodsItem.getGname());

            cartRepository.save(cart);
            return ResultUtils.success();
        }
    }

    public Result deleteFromCart(Integer cartId)
    {
        Cart cart = cartRepository.findCartByCartId(cartId);
        if(cart == null)
            return ResultUtils.error(MsgId.Cart_NO_CartID);

        cartRepository.delete(cart);
        return ResultUtils.success();
    }
}
