package com.example.demo.repository;

import com.example.demo.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author 赵思阳
 * @Date 2021/7/13 16:33
 * @Version 1.0
 */
public interface MerchantRepository extends JpaRepository<Merchant,Integer> {

    Merchant findMerchantByMerchantPhone(String phone);
    Merchant findMerchantByMerchantName(String name);
    Merchant findMerchantByMerchantId(Integer id);


}
