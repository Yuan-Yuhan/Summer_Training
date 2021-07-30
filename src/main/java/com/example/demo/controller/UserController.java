package com.example.demo.controller;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * @author 袁宇涵
 * @since 2021-07-23
 * @version 1.0
 */
//用户
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(defaultValue = "李斯", required = false,value = "xingming") String nikename){

        return nikename;
    }



    /**
     * 用户登录
     * @param phone,password
     * @return
     */
    @GetMapping ("/login")
    public Result login(@RequestParam("phone")String phone, @RequestParam("password")String password){
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
     * @param uid,gender,birthday,place
     * @return
     */
    @RequestMapping("/update")
    public Result update(Integer uid,String gender,String birthday,String place){
        return userService.update(uid,gender,birthday,place);
    }
    /**
     * 修改密码 id,password必传
     * @param uid,password
     * @return
     */
    @RequestMapping("/changePassword")
    public Result changePassword(Integer uid,String password){
        return userService.changePassword(uid,password);
    }

    /**
     * 用户删除，id必传
     * @param uid
     * @return
     */
    @RequestMapping("/delete" )
    public Result delete(Integer uid){
        return userService.delete(uid);
    }

    @RequestMapping("/findUserByUid")
    public Result findUserByUid(Integer uid)
    {
        User user = userRepository.findByUid(uid);
        if(user == null) //2,"用户不存在！"
            return ResultUtils.error(MsgId.USER_NOT_EXIST);

        return ResultUtils.success(user);
    }
    /**
     * 查找用户头像图片地址
     * @param uid
     * @return
     */
    public Result findAvatar(Integer uid){
        return ResultUtils.success(userRepository.findAvatarByUid(uid));
    }
    /**
     * uid查找用户
     * @param uid
     * @return
     */
    @RequestMapping("/findByUid")
    public Result findByUid(Integer uid){return ResultUtils.success(userRepository.findByUid(uid));}

}
