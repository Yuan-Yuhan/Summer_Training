package com.example.demo.repository;

import com.example.demo.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods,Integer> {
    //通过规格id查找商品规格
    Goods findGoodsByGid(int gid);
    //通过商品id查找所有商品规格
    List<Goods> findAllBySid(int sid);


}
