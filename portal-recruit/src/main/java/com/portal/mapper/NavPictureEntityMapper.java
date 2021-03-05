package com.portal.mapper;

import com.portal.pojo.NavPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/** 照片类接口 */
@Repository
public interface NavPictureEntityMapper
    extends JpaRepository<NavPictureEntity, Long>, JpaSpecificationExecutor<NavPictureEntity> {

    /**
     * 删除照片
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update NavPictureEntity set isDel = 1 where picId = ?1")
    void deletePictureEntityById(Long id);

    /**
     * 启用照片
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update NavPictureEntity set isDel = 0 where picId = ?1")
    void openPictureEntityById(Long id);

    /**
     * 审核不通过
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update NavPictureEntity set isPass = 1 where picId = ?1")
    void notPassPictureEntity(Long id);

    /**
     * 审核通过
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update NavPictureEntity set isPass = 0 where picId = ?1")
    void passPictureEntity(Long id);


    /**
     * 统计每个导航栏分类上传的照片数
     *
     * @param navId
     * @return
     */
    @Query(value = "select count(1) from nav_picture where nav_id = ?1 and is_del = 0 and is_pass = 0",nativeQuery = true)
    int navCatePicCount(Long navId);

    /**
     * 更新照片
     * @param url
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = "update nav_picture set url = ?1,size = ?3 where pic_id = ?2",nativeQuery = true)
    void updatePictureById(String url,Long id,Long size);
}
