package com.portal.mapper;

import com.portal.pojo.PosDetailWorkTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/** 职位信息和工作类型中间表 */
@Repository
public interface PosDetailWorkTypeEntityMapper
    extends JpaRepository<PosDetailWorkTypeEntity, Long>,
        JpaSpecificationExecutor<PosDetailWorkTypeEntity> {
    /**
     * 更新职位信息和工作类型中间表
     * @param typeId
     * @param detailId
     */
    @Transactional
    @Modifying
    @Query(value = "update pos_detail_work_type set type_id = ?1 where detail_id = ?2",nativeQuery = true)
    void updatePosDetailWorkTypeEntity(Long typeId,Long detailId);
}
