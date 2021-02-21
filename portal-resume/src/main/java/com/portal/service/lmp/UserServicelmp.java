package com.portal.service.lmp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.mapper.userDao;
import com.portal.pojo.User;
import com.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicelmp implements UserService {

    @Autowired
    private userDao userdao;

    @Override
    public boolean addUser(User user) {
        return userdao.addUser(user);
    }

    @Override
    public User queryUser(Long id) {
        return userdao.queryUser(id);
    }

    @Override
    public List<User> queryUserAll() {
        PageHelper.startPage(1,6);
        List<User> mylist = userdao.queryUserAll();
        PageInfo<User> pageInfo = new PageInfo<>(mylist);
        return pageInfo.getList();
    }

    @Override
    public Boolean addUserSelective(User user) {
        return userdao.addUserSelective(user);
    }

    @Override
    public Boolean deleteUser(Long id) {
        return userdao.deleteUser(id);
    }

    @Override
    public Boolean updateUserSelective(User user) {
        return userdao.updateUserSelective(user);
    }

    @Override
    public Boolean updateUserAll(User user) {
        return userdao.updateUserAll(user);
    }

    @Override
    public Boolean uploadUserImg(String user_photo_url) {
        return userdao.uploadUserImg(user_photo_url);
    }
}
