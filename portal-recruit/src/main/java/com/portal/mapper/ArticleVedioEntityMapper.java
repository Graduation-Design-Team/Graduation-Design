package com.portal.mapper;

import com.portal.pojo.ArticleVedioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/** 文章视频接口 */
@Repository
public interface ArticleVedioEntityMapper
    extends JpaRepository<ArticleVedioEntity, Long>, JpaSpecificationExecutor<ArticleVedioEntity> {

    /**
     * 删除视频
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update ArticleVedioEntity set isDel = 1 where vedioId = ?1")
    void deleteArticleVedioById(Long id);

    /**
     * 启用视频
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update ArticleVedioEntity set isDel = 0 where vedioId = ?1")
    void openArticleVedioById(Long id);

    /**
     * 审核不通过
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update ArticleVedioEntity set isPass = 1 where vedioId = ?1")
    void notPassArticleVedioEntity(Long id);

    /**
     * 审核通过
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update ArticleVedioEntity set isPass = 0 where vedioId = ?1")
    void passArticleVedioEntity(Long id);


    /**
     * 统计每个文章上传的视频数
     *
     * @param articleId
     * @return
     */
    @Query(value = "select count(1) from article_vedio where article_id = ?1 and is_del = 0 and is_pass = 0",nativeQuery = true)
    int articleCateArticleVedioCount(Long articleId);

    /**
     * 更新视频
     * @param url
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = "update article_vedio set url = ?1,size = ?3 where vedio_id = ?2",nativeQuery = true)
    void updateArticleVedioById(String url,Long id,Long size);
}
