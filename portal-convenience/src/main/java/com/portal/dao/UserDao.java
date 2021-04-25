package com.portal.dao;

import org.apache.ibatis.annotations.Param;

public interface UserDao {
    Integer getRoleIdByUserId(@Param("userId") Integer userId);
}
