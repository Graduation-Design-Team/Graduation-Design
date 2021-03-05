package com.portal.mapper;

import com.portal.pojo.PosDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/** 职位详细详细接口 */
@Repository
public interface PosDetailEntityMapper
    extends JpaRepository<PosDetailEntity, Long>, JpaSpecificationExecutor<PosDetailEntity> {

    /**
     * 根据id删除职位详细信息
     *
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = " update PosDetailEntity set detailIsDel =1 where detailId = ?1")
    void deletePosDetail(Long id);

    /**
     * 根据id启用职位详细信息
     *
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = "update PosDetailEntity set detailIsDel = 0 where detailId = ?1")
    void openPosDetail(Long id);
}