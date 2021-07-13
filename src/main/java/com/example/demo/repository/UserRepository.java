package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Integer> {

    //通过Id查找用户
    User findByUid(Integer uid);
    //通过电话查找用户
    User findByPhone(String phone);
    //通过昵称查找用户
    User findByNikename(String nikename);
    //通过电话查找密码
    String findPasswordByPhone(String phone);


}
