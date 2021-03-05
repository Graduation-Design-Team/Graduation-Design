package com.portal.service;

import com.portal.mapper.PosCategoryMapper;
import com.portal.pojo.PosCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

//保证事务的一致性
@Transactional
@Service
public class PosCategoryService {

    @Autowired private PosCategoryMapper posCategoryMapper;

    /**
     * 查询职位分裂
     *
     * @param id
     * @return
     */
    public List<PosCategoryEntity> getPosCategory(Long id) {
        Specification<PosCategoryEntity> spec =
                (Root<PosCategoryEntity> root,
                 CriteriaQuery<?> criteriaQuery,
                 CriteriaBuilder criteriaBuilder) -> {
                    Path<Object> posParentId = root.get("posParentId");
                    Path<Object> posIsDel = root.get("posIsDel");
                    Predicate p1 = criteriaBuilder.equal(posParentId, id);
                    Predicate p2 = criteriaBuilder.equal(posIsDel, 0);
                    return criteriaBuilder.and(p1, p2);
                };
        Sort sort = new Sort(Sort.Direction.ASC, "posSort");
        List<PosCategoryEntity> list = null;
        try {
            list = posCategoryMapper.findAll(spec, sort);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
        return list;
    }

    /**
     * 添加职位分类
     *
     * @param posCategoryEntity
     */
    public void addPosCategory(PosCategoryEntity posCategoryEntity) {
        try {
            posCategoryMapper.save(posCategoryEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败");
        }
    }

    /**
     * 更新职位分类
     *
     * @param posCategoryEntity
     */
    public void updatePosCategory(PosCategoryEntity posCategoryEntity) {
        try {
            int isDel = posCategoryEntity.getPosIsDel();
            if(isDel != 0 && isDel != 1) {
                //默认删除状态设置为0
                posCategoryEntity.setPosIsDel((byte)0);
            }
            posCategoryMapper.save(posCategoryEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新失败");
        }
    }

    /**
     * 删除职位分类
     *
     * @param id
     */
    public void deletePosCategory(Long id) {
        try {
            if (posCategoryMapper.existsById(id)) {
                posCategoryMapper.deletePosCategory(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败");
        }
    }

    /** 启用被删除的分类 */
    public void openPosCategory(Long id) {
        try {
            if(posCategoryMapper.existsById(id)) {
                posCategoryMapper.openPosCategory(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("启用失败");
        }
    }
}
