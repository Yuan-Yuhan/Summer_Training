package com.example.demo.repository;

import com.example.demo.entity.GoodsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsItemRepository extends JpaRepository<GoodsItem,Integer> {
     //模糊查询，输入商品名称返回所有符合的商品
      List<GoodsItem> findAllByGnameLike(String gname);
      //通过商品id查找一个商品
      GoodsItem findBySid(int sid);

//      //查找六个商品
//      List<GoodsItem> findFirst6ByOrderBySidAsc(int sid);

}