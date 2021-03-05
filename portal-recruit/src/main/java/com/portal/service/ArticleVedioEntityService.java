package com.portal.service;

import com.portal.mapper.ArticleEntityMapper;
import com.portal.mapper.ArticleVedioEntityMapper;
import com.portal.pojo.ArticleVedioEntity;
import com.portal.pojo.NavPictureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

//保证事务的一致性
@Transactional
@Service
public class ArticleVedioEntityService {

    @Autowired
    private ArticleVedioEntityMapper articleVedioEntityMapper;

    @Autowired
    private ArticleEntityMapper articleEntityMapper;

    /**
     * 获取视频列表
     *
     * @return
     */
    public List<ArticleVedioEntity> search(Long id) {
        Specification<ArticleVedioEntity> spec =
                (Root<ArticleVedioEntity> root,
                 CriteriaQuery<?> criteriaQuery,
                 CriteriaBuilder criteriaBuilder) -> {
                    Path<Object> articleId = root.get("articleId");
                    Path<Object> isDel = root.get("isDel");
                    Path<Object> isPass = root.get("isPass");
                    Predicate p1 = criteriaBuilder.equal(articleId, id);
                    Predicate p2 = criteriaBuilder.equal(isDel, 0);
                    Predicate p3 = criteriaBuilder.equal(isPass, 0);
                    return criteriaBuilder.and(p1, p2,p3);
                };
        List<ArticleVedioEntity> list = null;
        try {
            list = articleVedioEntityMapper.findAll(spec);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
        return list;
    }

    /** 添加视频 */
    public void addVedioEntity(String url, Long articleId, Long size) {
        try {
            articleVedioEntityMapper.save(new ArticleVedioEntity(
                    articleId, size, url, NavPictureEntity.newDateTime(), (byte) 0, (byte) 1));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败");
        }
    }

    /** 更新视频 */
    public void updateVedioEntity(String url, Long vedioId,Long size) {
        try {
            articleVedioEntityMapper.updateArticleVedioById(url, vedioId, size);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新失败");
        }
    }

    /**
     * 删除视频
     *
     * @param id
     */
    public void deleteVedioEntity(Long id) {
        try {
            articleVedioEntityMapper.deleteArticleVedioById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败");
        }
    }

    /**
     * 启用视频
     *
     * @param id
     */
    public void openVedioEntity(Long id) {
        try {
            articleVedioEntityMapper.openArticleVedioById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("启用失败");
        }
    }

    /**
     * 审核不通过
     *
     * @param id
     */
    public void notPassVedioEntity(Long id) {
        try {
            articleVedioEntityMapper.notPassArticleVedioEntity(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("审核不通过失败");
        }
    }

    /**
     * 审核通过
     *
     * @param id
     */
    public void passVedioEntity(Long id) {
        try {
            articleVedioEntityMapper.passArticleVedioEntity(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("审核通过失败");
        }
    }

    /**
     * 判断文章id是否存在
     *
     * @param id
     * @return
     */
    public boolean existsByArticleId(Long id) {
        try {
            return articleEntityMapper.existsById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("执行失败");
        }
    }


    /**
     * 判断视频是否存在
     *
     * @param id
     * @return
     */
    public boolean existsByVedioId(Long id) {
        try {
            return articleVedioEntityMapper.existsById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("执行失败");
        }
    }

    /**
     * 统计每个文章所上传的视频数
     *
     * @param articleId
     * @return
     */
    public int articleCateVedioCount(Long articleId) {
        try {
            return articleVedioEntityMapper.articleCateArticleVedioCount(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("执行失败");
        }
    }
}
