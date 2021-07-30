package com.example.demo.repository;

import com.example.demo.entity.Stars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */
public interface StarsRepository extends JpaRepository<Stars, Integer>
{
    Stars findStarsByGname(String gname);

    List<Stars> findAllByMerchantId(Integer merchantId);
}
