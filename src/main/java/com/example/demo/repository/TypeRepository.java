package com.example.demo.repository;

import com.example.demo.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */
public interface TypeRepository extends JpaRepository<Type, Integer>
{
    //通过MerchantID找到所有的type
    List<Type> findAllByMerchantId(Integer merchantId);

    Type findTypeByTypeId(Integer typeId);
}
