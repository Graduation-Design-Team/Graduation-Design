package com.portal.mapper;

import com.portal.pojo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/** 用户接口 */
@Repository
public interface UserEntityMapper
    extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

}
