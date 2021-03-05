package com.portal.mapper;

import com.portal.pojo.ArticlePictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/** 文章照片接口 */
@Repository
public interface ArticlePictureEntityMapper
    extends JpaRepository<ArticlePictureEntity, Long>,
        JpaSpecificationExecutor<ArticlePictureEntity> {

    /**
     * 删除照片
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update ArticlePictureEntity set isDel = 1 where picId = ?1")
    void deletePictureEntityById(Long id);

    /**
     * 启用照片
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update ArticlePictureEntity set isDel = 0 where picId = ?1")
    void openPictureEntityById(Long id);

    /**
     * 审核不通过
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update ArticlePictureEntity set isPass = 1 where picId = ?1")
    void notPassPictureEntity(Long id);

    /**
     * 审核通过
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update ArticlePictureEntity set isPass = 0 where picId = ?1")
    void passPictureEntity(Long id);



    /**
     * 更新照片
     * @param url
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = "update article_picture set url = ?1,size = ?3 where pic_id = ?2",nativeQuery = true)
    void updatePictureById(String url,Long id,Long size);
}
