package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

//    写接口  接受 用户的输入
    @RequestMapping("/hello")
    public String hello(@RequestParam(defaultValue = "李斯", required = false,value = "xingming") String name){

        return name;
    }

    @RequestMapping("/login")
    public String login(@RequestParam(defaultValue = "李斯", required = false,value = "xingming") String name){
        return name;
    }

    @RequestMapping("/add")
    public String add(@RequestParam(defaultValue = "李斯", required = false) String name){
        User user= new User();
        user.setBirthday("1987");
        user.setNikename(name);
        userRepository.save(user);
        return name;
    }
}
