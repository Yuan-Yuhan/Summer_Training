package com.example.demo.repository;

import com.example.demo.entity.Showcase;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */
public interface ShowcaseRepository extends JpaRepository<Showcase, Integer>
{
    Showcase findShowcaseByMerchantId(Integer merchantId);
}
