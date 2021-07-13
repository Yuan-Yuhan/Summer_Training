package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.servce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(defaultValue = "李斯", required = false,value = "xingming") String nikename){

        return nikename;
    }



    /**
     * 用户登录
     * @param phone,password
     * @return
     */
    @RequestMapping ("/login")
    public Result login(String phone, String password){
        return userService.login(phone,password);
    }

    /**
     * 用户注册
     * @param phone,nikename,password
     * @return
     */
    @RequestMapping("/register")
    public Result register(String phone, String nikename,String password){
        return userService.register(phone,nikename,password);
    }
    /**
     * 用户更新 id必传
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public Result update(User user){
        return userService.update(user);
    }

    /**
     * 用户删除，id必传
     * @param uid
     * @return
     */
    @RequestMapping("/delete" )
    public Result delet(int uid){
        return userService.delete(uid);
    }



}