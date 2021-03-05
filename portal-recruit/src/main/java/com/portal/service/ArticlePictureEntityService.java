package com.portal.service;


import com.portal.mapper.ArticlePictureEntityMapper;
import com.portal.pojo.ArticlePictureEntity;
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
public class ArticlePictureEntityService {

    @Autowired
    private ArticlePictureEntityMapper articlePictureEntityMapper;


    /**
     * 获取照片列表
     *
     * @return
     */
    public List<ArticlePictureEntity> search() {
        Specification<ArticlePictureEntity> spec =
                (Root<ArticlePictureEntity> root,
                 CriteriaQuery<?> criteriaQuery,
                 CriteriaBuilder criteriaBuilder) -> {
                    Path<Object> isDel = root.get("isDel");
                    Path<Object> isPass = root.get("isPass");
                    Predicate p1 = criteriaBuilder.equal(isDel, 0);
                    Predicate p2 = criteriaBuilder.equal(isPass, 0);
                    return criteriaBuilder.and(p1,p2);
                };
        List<ArticlePictureEntity> list = null;
        try {
            list = articlePictureEntityMapper.findAll(spec);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
        return list;
    }

    /** 添加照片 */
    public void addPictureEntity(String url, Long size) {
        try {
            articlePictureEntityMapper.save(
                    new ArticlePictureEntity(size, url, NavPictureEntity.newDateTime(), (byte) 0, (byte) 1));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败");
        }
    }

    /** 更新照片 */
    public void updatePictureEntity(String url, Long picId,Long size) {
        try {
            articlePictureEntityMapper.save(
                    new ArticlePictureEntity(picId,size, url, NavPictureEntity.newDateTime(), (byte) 0, (byte) 1));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新失败");
        }
    }

    /**
     * 删除照片
     *
     * @param id
     */
    public void deletePictureEntity(Long id) {
        try {
            articlePictureEntityMapper.deletePictureEntityById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败");
        }
    }

    /**
     * 启用照片
     *
     * @param id
     */
    public void openPictureEntity(Long id) {
        try {
            articlePictureEntityMapper.openPictureEntityById(id);
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
    public void notPassPictureEntity(Long id) {
        try {
            articlePictureEntityMapper.notPassPictureEntity(id);
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
    public void passPictureEntity(Long id) {
        try {
            articlePictureEntityMapper.passPictureEntity(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("审核通过失败");
        }
    }

    /**
     * 判断导航栏分类是否存在
     *
     * @param id
     * @return
     */
    public boolean existsByNavId(Long id) {
        try {
            return articlePictureEntityMapper.existsById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("执行失败");
        }
    }


    /**
     * 判断照片Id是否存在
     *
     * @param id
     * @return
     */
    public boolean existsByPicId(Long id) {
        try {
            return articlePictureEntityMapper.existsById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("执行失败");
        }
    }
}
