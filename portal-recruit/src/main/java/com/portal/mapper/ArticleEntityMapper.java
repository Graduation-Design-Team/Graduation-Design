package com.portal.mapper;

import com.portal.pojo.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ArticleEntityMapper
    extends JpaRepository<ArticleEntity, Long>, JpaSpecificationExecutor<ArticleEntity> {

    @Transactional
    @Modifying
    @Query("update ArticleEntity set isDel = 1 where articleId = ?1")
    void deleteArticleEntityById(Long id);

    @Transactional
    @Modifying
    @Query("update ArticleEntity set isDel = 0 where articleId = ?1")
    void openArticleEntityById(Long id);

    @Transactional
    @Modifying
    @Query("update ArticleEntity set isPass = 1 where articleId = ?1")
    void notPassArticleEntityById(Long id);

    @Transactional
    @Modifying
    @Query("update ArticleEntity set isPass = 0 where articleId = ?1")
    void passArticleEntityById(Long id);
}
