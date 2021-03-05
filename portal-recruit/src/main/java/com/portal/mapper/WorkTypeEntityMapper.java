package com.portal.mapper;

import com.portal.pojo.WorkTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 职位类型类接口
 */
@Repository
public interface WorkTypeEntityMapper
    extends JpaRepository<WorkTypeEntity, Long>, JpaSpecificationExecutor<WorkTypeEntity> {

    @Transactional
    @Modifying
    @Query("update WorkTypeEntity set isDel = 1 where typeId = ?1")
    void deleteWorkType(Long id);

    @Transactional
    @Modifying
    @Query("update WorkTypeEntity set isDel = 0 where typeId = ?1")
    void openWorkType(Long id);
}
