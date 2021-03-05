package com.portal.service;


import com.portal.mapper.NavCategoryMapper;
import com.portal.mapper.NavVedioEntityMapper;
import com.portal.pojo.NavPictureEntity;
import com.portal.pojo.NavVedioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

//保证事务的一致性
@Transactional
@Service
public class NavVedioEntityService {

    @Autowired
    private NavVedioEntityMapper navVedioEntityMapper;

    @Autowired private NavCategoryMapper navCategoryMapper;

    /**
     * 获取视频列表
     *
     * @return
     */
    public List<NavVedioEntity> search(Long id) {
        Specification<NavVedioEntity> spec =
                (Root<NavVedioEntity> root,
                 CriteriaQuery<?> criteriaQuery,
                 CriteriaBuilder criteriaBuilder) -> {
                    Path<Object> navId = root.get("navId");
                    Path<Object> isDel = root.get("isDel");
                    Path<Object> isPass = root.get("isPass");
                    Predicate p1 = criteriaBuilder.equal(navId, id);
                    Predicate p2 = criteriaBuilder.equal(isDel, 0);
                    Predicate p3 = criteriaBuilder.equal(isPass, 0);
                    return criteriaBuilder.and(p1, p2,p3);
                };
        List<NavVedioEntity> list = null;
        try {
            list = navVedioEntityMapper.findAll(spec);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
        return list;
    }

    /** 添加视频 */
    public void addVedioEntity(String url, Long navId, Long size) {
        try {
            navVedioEntityMapper.save(
                    new NavVedioEntity(navId, size, url, NavPictureEntity.newDateTime(), (byte) 0, (byte) 1));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败");
        }
    }

    /** 更新视频 */
    public void updateVedioEntity(String url, Long picId,Long size) {
        try {
            navVedioEntityMapper.updateNavVedioById(url, picId, size);
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
            navVedioEntityMapper.deleteNavVedioById(id);
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
            navVedioEntityMapper.openNavVedioById(id);
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
            navVedioEntityMapper.notPassNavVedioEntity(id);
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
            navVedioEntityMapper.passNavVedioEntity(id);
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
            return navCategoryMapper.existsById(id);
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
            return navVedioEntityMapper.existsById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("执行失败");
        }
    }

    /**
     * 统计每个导航栏分类所上传的视频数
     *
     * @param navId
     * @return
     */
    public int navCateVedioCount(Long navId) {
        try {
            return navVedioEntityMapper.navCateNavVedioCount(navId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("执行失败");
        }
    }
}
