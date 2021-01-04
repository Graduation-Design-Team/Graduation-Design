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
        user.setId(1L);
        user.setAge(18);
        user.setBirthday(new Date());
        user.setCreated(new Date());
        user.setName("xiaohai");
        user.setPassword("123");
        user.setSex(0);
        user.setUpdated(new Date());
        user.setUserName("lalal");
        list.add(user);
        return list;
    }

}
