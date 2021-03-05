package com.portal.mapper;


import com.portal.pojo.PosCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 职位分类接口
 */
@Repository
public interface PosCategoryMapper extends JpaRepository<PosCategoryEntity,Long>, JpaSpecificationExecutor<PosCategoryEntity> {

    /**
     * 根据id删除职位分类
     *
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = " update PosCategoryEntity set posIsDel =1 where posId = ?1")
    void deletePosCategory(Long id);

    /**
     * 根据id启用职位分类
     *
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = "update PosCategoryEntity set posIsDel = 0 where posId = ?1")
    void openPosCategory(Long id);
}
