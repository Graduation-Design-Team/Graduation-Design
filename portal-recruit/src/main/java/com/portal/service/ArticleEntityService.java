package com.portal.service;


import com.portal.mapper.ArticleEntityMapper;
import com.portal.pojo.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

//保证事务的一致性
@Transactional
@Service
public class ArticleEntityService {

    @Autowired
    private ArticleEntityMapper articleEntityMapper;

    /**
     * 获取文章列表
     * @return
     */
    public List<ArticleEntity> getArticleEntity() {
        Specification<ArticleEntity> spec = (Root<ArticleEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            Path<Object> isDel = root.get("isDel");
            Path<Object> isPass = root.get("isPass");
            // 0 没删除 1 删除
            Predicate p1 = criteriaBuilder.equal(isDel, 0);
            // 0 通过审核 1审核中
            Predicate p2 = criteriaBuilder.equal(isPass, 0);
            return criteriaBuilder.and(p1,p2);
        };
        List<ArticleEntity> list = null;
        try {
            list = articleEntityMapper.findAll(spec);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取失败");
        }
        return list;
    }

    /**
     * 添加文章
     * @param documentEntity
     */
    public void addArticleEntity(ArticleEntity documentEntity) {
        try {
            documentEntity.setArticleCreatedTime(ArticleEntity.newDateTime());
            articleEntityMapper.save(documentEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败");
        }
    }


    /**
     * 更新文章
     * @param documentEntity
     */
    public void updateArticleEntity(ArticleEntity documentEntity) {
        try {
            documentEntity.setArticleCreatedTime(ArticleEntity.newDateTime());
            int isDel = documentEntity.getIsDel();
            if(isDel != 0 && isDel != 1) {
                documentEntity.setIsDel((byte)0);
            }
            articleEntityMapper.save(documentEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新失败");
        }
    }


    /**
     * 删除文章
     * @param id
     */
    public void deleteArticleEntity(Long id) {
        try {
            if(articleEntityMapper.existsById(id)) {
                articleEntityMapper.deleteArticleEntityById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败");
        }
    }


    /**
     * 启用文章
     * @param id
     */
    public void openArticleEntity(Long id) {
        try {
            if(articleEntityMapper.existsById(id)) {
                articleEntityMapper.openArticleEntityById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("启用失败");
        }
    }


    /**
     * 审核不通过
     * @param id
     */
    public void notPassArticleEntityById(Long id) {
        try {
            if(articleEntityMapper.existsById(id)) {
                articleEntityMapper.notPassArticleEntityById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("审核不通过失败");
        }
    }

    /**
     * 审核通过
     * @param id
     */
    public void passArticleEntityById(Long id) {
        try {
            if(articleEntityMapper.existsById(id)) {
                articleEntityMapper.passArticleEntityById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("审核通过失败");
        }
    }
}
