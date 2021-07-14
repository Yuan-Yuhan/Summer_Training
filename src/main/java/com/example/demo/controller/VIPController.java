package com.example.demo.controller;

import com.example.demo.servce.VIPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 赵思阳
 * @Date 2021/7/14 10:33
 * @Version 1.0
 */
@RestController
@RequestMapping("/vip")
public class VIPController {
    @Autowired
    VIPService vipService;
}
