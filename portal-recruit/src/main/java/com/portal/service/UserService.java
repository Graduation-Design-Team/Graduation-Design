package com.portal.service;

import com.portal.mapper.UserMapper;
import com.portal.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取所有用户的信息
     * @return
     */
    public List<User> getAllUser() {
        //这里不做数据库操作，只做模拟
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setUserId(1);
        user.setUserAge(18);
        user.setUserBirthday(new Date());
        ;
        user.setUserName("xiaohai");
        user.setUserPassword("123");


        user.setUserName("lalal");
        list.add(user);
        return list;
    }

}
