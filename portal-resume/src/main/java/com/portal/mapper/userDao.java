package com.portal.mapper;

import com.portal.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface userDao {

    Boolean addUser(User user);

    Boolean addUserSelective(User user);

    User queryUser(Long id);

    List<User> queryUserAll();

    Boolean deleteUser(Long id);

    Boolean updateUserSelective(User user);

    Boolean updateUserAll(User user);

    Boolean uploadUserImg(String user_photo_url);
}
