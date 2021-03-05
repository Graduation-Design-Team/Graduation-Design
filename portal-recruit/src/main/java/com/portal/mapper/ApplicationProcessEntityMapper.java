package com.portal.mapper;

import com.portal.pojo.ApplicationProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/** 用户应聘进程接口 */
@Repository
public interface ApplicationProcessEntityMapper
    extends JpaRepository<ApplicationProcessEntity, Long>,
        JpaSpecificationExecutor<ApplicationProcessEntity> {

    /**
     * 查询最新的应聘进度记录
     * @param appId
     * @return
     */
    @Query(value = "select * from application_process where app_id = ?1 order by created_time desc limit 1",nativeQuery = true)
    ApplicationProcessEntity findLastestRecord(Long appId);

}
