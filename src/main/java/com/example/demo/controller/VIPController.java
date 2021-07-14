package com.example.demo.controller;

import com.example.demo.service.VIPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 赵思阳
 * @since 2021/7/13
 * @version 1.0
 */
@RestController
@RequestMapping("/vip")
public class VIPController {
    @Autowired
    VIPService vipService;
}
