package com.example.demo.repository;

import com.example.demo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */
public interface CartRepository extends JpaRepository<Cart, Integer>
{
    Cart findCartByCartId(Integer cartId);

    List<Cart> findAllByUid(Integer uid);

    Cart findByUidAndSidAndGid(Integer uid, Integer sid, Integer gid);
}
