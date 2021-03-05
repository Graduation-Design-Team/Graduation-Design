package com.portal.mapper;

import com.portal.pojo.NavCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 导航栏分类接口
 */
@Repository
public interface NavCategoryMapper extends JpaRepository<NavCategoryEntity,Long>, JpaSpecificationExecutor<NavCategoryEntity> {

    /**
     * 根据id删除分类
     *
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = " update NavCategoryEntity set navIsDel =1 where navId = ?1")
    void deletePosCategory(Long id);

    /**
     * 根据id启用分类
     *
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = "update NavCategoryEntity set navIsDel = 0 where navId = ?1")
    void openPosCategory(Long id);
}
