package com.example.demo.service;


import com.example.demo.enm.MsgId;
import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.utils.ResultUtils;
/**
 * @author 赵思阳
 * @since 2021-07-23
 * @version 1.0
 */

//用户类
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     *用户登录
     */
    public Result login(String phone, String password) {
        User userExistPhone = userRepository.findByPhone(phone);

        if (userExistPhone != null) {
            String userExistPassword = userExistPhone.getPassword();
            if (userExistPassword.equals(password)) {
                return  ResultUtils.success(userExistPhone);
            } else {
                return ResultUtils.error(MsgId.USER_ERR_PASS);
            }
        } else {
            return ResultUtils.error(MsgId.USER_NOT_EXIST);
        }
    }

    /**
     *用户注册
     */
    public Result register(String phone, String nikename, String password) {


        User userExistPhone = userRepository.findByPhone(phone);
        User userExistNikename = userRepository.findByNikename(nikename);
        User user = new User();
        user.setPhone(phone);
        user.setNikename(nikename);
        user.setPassword(password);
        if (userExistPhone != null) {
            return ResultUtils.error(MsgId.PHONE_ALREADY_EXIST);
        } else if (userExistNikename != null) {
            return ResultUtils.error(MsgId.USER_ALREADY_EXIST);
        } else {
            userRepository.save(user);
            return ResultUtils.success();
        }

    }

    /**
     *用户信息更新
     */

    public Result update(Integer uid,String gender,String birthday,String place){
        if(uid==null){
            return ResultUtils.error(MsgId.NO_ID);
        }
        User user=userRepository.findByUid(uid);
        user.setGender(gender);
        user.setBirthday(birthday);
        user.setPlace(place);
        userRepository.save(user);
        return ResultUtils.success(user);

    }
    /**
     *用户修改密码
     */
    public Result changePassword(Integer uid,String password){
        if(uid==null){
            return ResultUtils.error(MsgId.NO_ID);
        }
        User user=userRepository.findByUid(uid);
        user.setPassword(password);
        return ResultUtils.success();
    }
    /**
     * 删除用户
     */

    public Result delete(Integer uid){
        User user=userRepository.findByUid(uid);
        if(user==null){
            return ResultUtils.error(MsgId.USER_NOT_EXIST);
        }
        userRepository.delete(user);
        return ResultUtils.success();

    }

}
