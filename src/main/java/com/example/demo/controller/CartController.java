package com.example.demo.controller;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */

import com.example.demo.entity.Result;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//购物车
@RestController
@RequestMapping("/cart")
public class CartController
{
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartService cartService;

    @RequestMapping("/add")
    public Result addToCart(Integer uid, Integer sid, Integer gid)
    {
        return cartService.addToCart(uid, sid, gid);
    }

    @RequestMapping("/delete")
    public Result deleteFromCart(Integer cartId)
    {
        return cartService.deleteFromCart(cartId);
    }

    @RequestMapping("/findUserCart")
    public Result findUserCart(Integer uid)
    {
        return ResultUtils.success(cartRepository.findAllByUid(uid));
    }

    @RequestMapping("/findCartByCartId")
    public Result findCartByCartId(Integer cartId)
    {
        return ResultUtils.success(cartRepository.findCartByCartId(cartId));
    }
}
