package com.portal.mapper;

import com.portal.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User>{

    @Select("select * from user")
    public List<User> getAllUser();
}
