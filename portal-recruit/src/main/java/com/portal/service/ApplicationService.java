package com.portal.service;

import com.portal.mapper.ApplicationMapper;
import com.portal.mapper.ApplicationProcessEntityMapper;
import com.portal.pojo.ApplicationEntity;
import com.portal.pojo.ApplicationProcessEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.criteria.*;
import java.util.List;

//保证事务的一致性
@Transactional
@Service
public class ApplicationService {

  @Autowired
  private ApplicationMapper applicationMapper;

  @Autowired
  private ApplicationProcessEntityMapper applicationProcessEntityMapper;


  /**
   * 查询用户的应聘记录
   *
   * @param id
   * @return
   */
  public List<ApplicationEntity> searchApplication(Long id) {
    Specification<ApplicationEntity> spec =
            (Root<ApplicationEntity> root,
             CriteriaQuery<?> criteriaQuery,
             CriteriaBuilder criteriaBuilder) -> {
              Path<Object> userId = root.get("userId");
              Path<Object> isDel = root.get("isDel");
              Predicate p1 = criteriaBuilder.equal(userId, id);
              Predicate p2 = criteriaBuilder.equal(isDel, 0);
              return criteriaBuilder.and(p1, p2);
            };
    List<ApplicationEntity> list = null;
    try {
      list = applicationMapper.findAll(spec);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("查询失败");
    }
    return list;
  }

  /**
   * 添加用户应聘记录
   *
   * @param userId
   * @param detailId
   */
  public void addApplication(Long userId, Long detailId) {
    try {
      ApplicationEntity newRecord = applicationMapper.save(
              new ApplicationEntity(userId, detailId, ApplicationEntity.newDateTime(), (byte) 0));
      int i = 10 / 0;
      applicationProcessEntityMapper.save(new ApplicationProcessEntity(newRecord.getAppId(), 1L,
              ApplicationProcessEntity.newDateTime()));
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("添加失败");
    }
  }

  @ApiOperation("更新用户应聘记录")
  @PostMapping("/update")
  public void updateApplication(Long appId, Long detailId) {
    try {
      applicationMapper.updateApplication(appId,detailId);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("更新失败");
    }
  }

  /**
   * 删除用户应聘记录
   *
   * @param appId
   */
  public void deleteApplication(Long appId) {
    try {
      applicationMapper.deleteApplication(appId);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("删除失败");
    }
  }

  /**
   * 启用用户应聘记录
   *
   * @param appId
   */
  public void openApplication(Long appId) {
    try {
      applicationMapper.openApplication(appId);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("启用失败");
    }
  }

}
