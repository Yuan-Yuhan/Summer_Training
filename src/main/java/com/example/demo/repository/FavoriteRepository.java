package com.example.demo.repository;

import com.example.demo.entity.Coupon;
import com.example.demo.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */
public interface FavoriteRepository extends JpaRepository<Favorite,Integer>
{
    Favorite findFavoriteByFavoriteId(Integer favoriteId);

    List<Favorite> findAllByUid(Integer uid);

    List<Favorite> findAllByMerchantId(Integer merchantId);

    Favorite findByUidAndSid(Integer uid, Integer sid);
}