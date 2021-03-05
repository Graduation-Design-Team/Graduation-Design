package com.portal.mapper;

import com.portal.pojo.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/** 招聘记录接口 */
@Repository
public interface ApplicationMapper
        extends JpaRepository<ApplicationEntity, Long>, JpaSpecificationExecutor<ApplicationEntity> {

    /**
     * 删除应聘记录
     * @param appId
     */
    @Transactional
    @Modifying
    @Query("update ApplicationEntity set isDel = 1 where appId = ?1")
    void deleteApplication(Long appId);


    /**
     * 启用应聘记录
     * @param appId
     */
    @Transactional
    @Modifying
    @Query("update ApplicationEntity set isDel = 0 where appId = ?1")
    void openApplication(Long appId);


    /**
     * 更新应聘信息
     * @param appId
     */
    @Transactional
    @Modifying
    @Query("update ApplicationEntity set detailId = ?2 where appId = ?1")
    void updateApplication(Long appId,Long detailId);
}
