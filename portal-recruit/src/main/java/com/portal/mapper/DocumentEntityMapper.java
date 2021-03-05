package com.portal.mapper;

import com.portal.pojo.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文档接口
 */
@Repository
public interface DocumentEntityMapper
    extends JpaRepository<DocumentEntity, Long>, JpaSpecificationExecutor<DocumentEntity> {

    /**
     * 删除文档
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update DocumentEntity set isDel = 1 where docId = ?1")
    void deleteDocumentEntityById(Long id);


    /**
     * 启用被删除文档
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update DocumentEntity set isDel = 0 where docId = ?1")
    void openDocumentEntityById(Long id);

    /**
     * 审核不通过
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update DocumentEntity set isPass = 1 where docId = ?1")
    void notPassDocumentEntityById(Long id);

    /**
     * 审核通过
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update DocumentEntity set isPass = 0 where docId = ?1")
    void passDocumentEntityById(Long id);
}
