package com.portal.service;


import com.portal.mapper.UserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//保证事务的一致性
@Transactional
@Service
public class UserEntityService {

    @Autowired
    private UserEntityMapper userEntityMapper;


    /**
     * 判断用户是否存在
     * @param id
     * @return
     */
    public boolean isExistsUserById(Long id) {
        try {
            return userEntityMapper.existsById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("执行失败");
        }
    }
}
