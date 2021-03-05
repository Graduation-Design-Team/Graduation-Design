package com.portal.service;


import com.portal.mapper.*;
import com.portal.pojo.CityPosDetailEntity;
import com.portal.pojo.PosDetailEntity;
import com.portal.pojo.PosDetailWorkTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

//保证事务的一致性
@Transactional
@Service
public class PosDetailEntityService {

    @Autowired
    private PosDetailEntityMapper posDetailEntityMapper;

    @Autowired
    private CityPosDetailEntityMapper cityPosDetailEntityMapper;

    @Autowired
    private CityEntityMapper cityEntityMapper;

    @Autowired
    private PosCategoryMapper posCategoryMapper;

    @Autowired
    private PosDetailWorkTypeEntityMapper posDetailWorkTypeEntityMapper;

    @Autowired
    private WorkTypeEntityMapper workTypeEntityMapper;

    /**
     * 获取职位详细信息
     * @param id
     * @return
     */
    public List<PosDetailEntity> getPosDetail(Long id, int currentPage, int size) {
        Specification<PosDetailEntity> spec = (Root<PosDetailEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->{
            Path<Object> posId = root.get("posId");
            Path<Object> detailIsDel = root.get("detailIsDel");
            Predicate p1 = criteriaBuilder.equal(posId, id);
            Predicate p2 = criteriaBuilder.equal(detailIsDel, 0);
            return criteriaBuilder.and(p1,p2);
        };
        List<PosDetailEntity> list = null;
        Pageable pageable = PageRequest.of(currentPage, size);
        try {
            Page<PosDetailEntity> all = posDetailEntityMapper.findAll(spec, pageable);
            list = all.getContent();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
        return list;
    }

    /**
     * 添加职位详细信息
     * @param posDetailEntity
     */

    public void addPosDetail(PosDetailEntity posDetailEntity, Long id, Long count, Long typeId) {
        try {
            //返回的对象的主键是新生成记录的id
            PosDetailEntity save = posDetailEntityMapper.save(posDetailEntity);
            //往职位城市中间表插入数据
            cityPosDetailEntityMapper.save(new CityPosDetailEntity(save.getDetailId(), id, count));
            // 添加职位工作类别中间表的信息
            posDetailWorkTypeEntityMapper.save(new PosDetailWorkTypeEntity(typeId, save.getDetailId()));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败");
        }
    }

    /**
     * 更新职位详细信息
     * @param posDetailEntity
     */
    public void updatePosDetail(PosDetailEntity posDetailEntity) {
        try {
            posDetailEntityMapper.save(posDetailEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新失败");
        }
    }

    /**
     * 删除职位信息
     * @param id
     */
    public void deletePosDetail(Long id) {
        try {
            if (posDetailEntityMapper.existsById(id)) {
                posDetailEntityMapper.deletePosDetail(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败");
        }
    }

    /**
     * 启用职位
     * @param id
     */
    public void openPosDetail(Long id) {
        try {
            if (posDetailEntityMapper.existsById(id)) {
                posDetailEntityMapper.openPosDetail(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("启用失败");
        }
    }

    /**
     * 添加职位详细信息的同时往中间表 插入职位id、城市id、数量的信息
     *
     * @param cityPosDetailEntity
     */
    public void addCityPosDetail(CityPosDetailEntity cityPosDetailEntity) {
        try {
            cityPosDetailEntityMapper.save(cityPosDetailEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败");
        }
    }

    /**
     * 更新职位的数量
     *
     * @param cityPosDetailEntity
     */
    public void updateCityPosDetailCount(CityPosDetailEntity cityPosDetailEntity) {
        try {
            cityPosDetailEntityMapper.save(cityPosDetailEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新失败");
        }
    }

    /**
     * 根据id查询城市的信息是否存在
     *
     * @param id
     * @return
     */
    public boolean existsCityByid(Long id) {
        try {
            return cityEntityMapper.existsById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("执行失败");
        }
    }


    /**
     * 判断该职位类别是否存在
     *
     * @param id
     * @return
     */
    public boolean existsPosCategory(Long id) {
        try {
            return posCategoryMapper.existsById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("执行失败");
        }
    }

    /**
     * 更新城市职位中间表的信息
     *
     * @param detailId
     * @param cityId
     * @param count
     */
    public void updateCityPosDetail(Long detailId, Long cityId, Long count, Long typeId) {
        try {
            cityPosDetailEntityMapper.updateCityPosDetail(detailId, cityId, count);
            posDetailWorkTypeEntityMapper.updatePosDetailWorkTypeEntity(typeId, detailId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新失败");
        }
    }

    /**
     * 根据id判断职位详细信息是否存在
     *
     * @param id
     * @return
     */
    public boolean existsPosDetailById(Long id) {
        try {
            return posDetailEntityMapper.existsById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("执行失败");
        }
    }


    /**
     * 根据id判断该职位类型是否存在
     *
     * @param id
     * @return
     */
    public boolean existsPosDetailWorkTypeById(Long id) {
        try {
            return workTypeEntityMapper.existsById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("执行失败");
        }
    }
}
