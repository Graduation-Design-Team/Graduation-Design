package com.portal.mapper;

import com.portal.pojo.NavVedioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/** 分类视频接口 */
@Repository
public interface NavVedioEntityMapper
    extends JpaRepository<NavVedioEntity, Long>, JpaSpecificationExecutor<NavVedioEntity> {
    /**
     * 删除视频
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update NavVedioEntity set isDel = 1 where vedioId = ?1")
    void deleteNavVedioById(Long id);

    /**
     * 启用视频
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update NavVedioEntity set isDel = 0 where vedioId = ?1")
    void openNavVedioById(Long id);

    /**
     * 审核不通过
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update NavVedioEntity set isPass = 1 where vedioId = ?1")
    void notPassNavVedioEntity(Long id);

    /**
     * 审核通过
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update NavVedioEntity set isPass = 0 where vedioId = ?1")
    void passNavVedioEntity(Long id);


    /**
     * 统计每个导航栏分类上传的视频数
     *
     * @param navId
     * @return
     */
    @Query(value = "select count(1) from nav_vedio where nav_id = ?1 and is_del = 0 and is_pass = 0",nativeQuery = true)
    int navCateNavVedioCount(Long navId);

    /**
     * 更新视频
     * @param url
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = "update nav_vedio set url = ?1,size = ?3 where vedio_id = ?2",nativeQuery = true)
    void updateNavVedioById(String url,Long id,Long size);
}
