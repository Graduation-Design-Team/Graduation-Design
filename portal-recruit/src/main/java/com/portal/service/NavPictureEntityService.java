package com.portal.service;

import com.portal.mapper.NavCategoryMapper;
import com.portal.mapper.NavPictureEntityMapper;
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
public class NavPictureEntityService {

  @Autowired
  private NavPictureEntityMapper navPictureEntityMapper;

  @Autowired private NavCategoryMapper navCategoryMapper;

  /**
   * 获取照片列表
   *
   * @return
   */
  public List<NavPictureEntity> search(Long id) {
    Specification<NavPictureEntity> spec =
            (Root<NavPictureEntity> root,
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
    List<NavPictureEntity> list = null;
    try {
      list = navPictureEntityMapper.findAll(spec);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("查询失败");
    }
    return list;
  }

  /** 添加照片 */
  public void addPictureEntity(String url, Long navId, Long size) {
    try {
      navPictureEntityMapper.save(
              new NavPictureEntity(navId, size, url, NavPictureEntity.newDateTime(), (byte) 0, (byte) 1));
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("添加失败");
    }
  }

  /** 更新照片 */
  public void updatePictureEntity(String url, Long picId,Long size) {
    try {
      navPictureEntityMapper.updatePictureById(url, picId, size);
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
      navPictureEntityMapper.deletePictureEntityById(id);
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
      navPictureEntityMapper.openPictureEntityById(id);
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
      navPictureEntityMapper.notPassPictureEntity(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("审核不同过失败");
    }
  }

  /**
   * 审核通过
   *
   * @param id
   */
  public void passPictureEntity(Long id) {
    try {
      navPictureEntityMapper.passPictureEntity(id);
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
   * 判断照片Id是否存在
   *
   * @param id
   * @return
   */
  public boolean existsByPicId(Long id) {
    try {
      return navPictureEntityMapper.existsById(id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("执行失败");
    }
  }

  /**
   * 统计每个导航栏分类所上传的照片数
   *
   * @param navId
   * @return
   */
  public int navCatePicCount(Long navId) {
    try {
      return navPictureEntityMapper.navCatePicCount(navId);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("执行失败");
    }
  }
}
