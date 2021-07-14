package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author 赵思阳
 * @since 2021/7/12
 * @version 1.0
 */
public interface UserRepository  extends JpaRepository<User, Integer> {

    //通过Id查找用户
    User findByUid(Integer uid);
    //通过电话查找用户
    User findByPhone(String phone);
    //通过昵称查找用户
    User findByNikename(String nikename);
    //通过电话查找密码
    String findPasswordByPhone(String phone);
    //通过id查找用户头像
    String findAvatarByUid(Integer uid);


}
