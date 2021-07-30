package com.example.demo.repository;

import com.example.demo.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */
public interface GoodsRepository extends JpaRepository<Goods,Integer> {
    //通过规格id查找商品规格
    Goods findGoodsByGid(Integer gid);
    //通过商品id查找所有商品规格
    List<Goods> findAllBySid(Integer sid);




}
