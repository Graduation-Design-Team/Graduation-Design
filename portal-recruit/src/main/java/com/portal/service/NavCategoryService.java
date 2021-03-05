package com.portal.service;

import com.portal.mapper.NavCategoryMapper;
import com.portal.pojo.NavCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * 导航栏分类Service
 */
//保证事务的一致性
@Transactional
@Service
public class NavCategoryService {

    @Autowired()
    private NavCategoryMapper navCategoryMapper;

    /**
     * 查询导航栏分类
     *
     * @param id
     * @return
     */
    public List<NavCategoryEntity> getNavCategoryById(Long id) {
        // 构造查询条件
        Specification<NavCategoryEntity> spec =
                (Root<NavCategoryEntity> root,
                 CriteriaQuery<?> criteriaQuery,
                 CriteriaBuilder criteriaBuilder) -> {
                    Path<Object> navParentId = root.get("navParentId");
                    Path<Object> navIsDel = root.get("navIsDel");
                    Predicate p1 = criteriaBuilder.equal(navParentId, id);
                    Predicate p2 = criteriaBuilder.equal(navIsDel, 0);
                    return criteriaBuilder.and(p1, p2);
                };

        // 升序
        Sort sort = new Sort(Sort.Direction.ASC, "navSort");
        List<NavCategoryEntity> list = null;
        try {
            list = navCategoryMapper.findAll(spec, sort);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
        return list;
    }

    /**
     * 添加导航栏分类
     *
     * @param navCategoryEntity
     */
    public void addNavCategory(NavCategoryEntity navCategoryEntity) {
        try {
            navCategoryMapper.save(navCategoryEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败");
        }
    }

    /**
     * 修改导航栏分类
     *
     * @param navCategoryEntity
     */
    public void updateNavCategory(NavCategoryEntity navCategoryEntity) {
        try {
            int isDel = navCategoryEntity.getNavIsDel();
            if (isDel != 0 && isDel != 1) {
                //设置默认的删除状态为0
                navCategoryEntity.setNavIsDel((byte) 0);
            }
            navCategoryMapper.save(navCategoryEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新失败");
        }
    }

    /**
     * 删除导航栏分类
     *
     * @param id
     */
    public void deleteNavCategory(Long id) {
        try {
            if (navCategoryMapper.existsById(id)) {
                navCategoryMapper.deletePosCategory(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败");
        }
    }

    /**
     * 开启被删除的导航分类
     *
     * @param id
     */
    public void openNavCategory(Long id) {
        try {
            if (navCategoryMapper.existsById(id)) {
                navCategoryMapper.openPosCategory(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("启用失败");
        }
    }
}
