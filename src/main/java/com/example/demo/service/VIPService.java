package com.example.demo.service;

import com.example.demo.repository.VIPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 赵思阳
 * @Date 2021/7/14 10:31
 * @Version 1.0
 */
@Service
public class VIPService {
    @Autowired
    VIPRepository repository;

}
