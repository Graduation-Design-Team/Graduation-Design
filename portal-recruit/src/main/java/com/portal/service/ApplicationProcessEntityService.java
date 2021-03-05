package com.portal.service;

import com.portal.mapper.ApplicationMapper;
import com.portal.mapper.ApplicationProcessEntityMapper;
import com.portal.pojo.ApplicationProcessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

//保证事务的一致性
@Transactional
@Service
public class ApplicationProcessEntityService {

    @Autowired private ApplicationProcessEntityMapper applicationProcessEntityMapper;

    @Autowired private ApplicationMapper applicationMapper;

    /**
     * 查询用户的应聘记录
     *
     * @param id
     * @return
     */
    public List<ApplicationProcessEntity> search(Long id) {
        Specification<ApplicationProcessEntity> spec =
                (Root<ApplicationProcessEntity> root,
                 CriteriaQuery<?> criteriaQuery,
                 CriteriaBuilder criteriaBuilder) -> {
                    Path<Object> appId = root.get("appId");
                    Predicate p1 = criteriaBuilder.equal(appId, id);
                    return p1;
                };

        List<ApplicationProcessEntity> list = null;
        try {
            list = applicationProcessEntityMapper.findAll(spec);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
        return list;
    }

    /**
     * 保存应聘记录
     *
     * @param applicationProcessEntity
     */
    public void add(ApplicationProcessEntity applicationProcessEntity) {
        try {
            // 查询最新的插入记录
            ApplicationProcessEntity lastestRecord =
                    applicationProcessEntityMapper.findLastestRecord(applicationProcessEntity.getAppId());
            // appId不存在
            if (lastestRecord == null) {
                return;
            }
            // 如果processCode = 0说明面试不通过
            if (applicationProcessEntity.getProcessCode() != null
                    && applicationProcessEntity.getProcessCode() == 0) {
                // 用户没有面试失败的记录，才需要添加，并且用户已经拿到offer了不需要添加
                if (lastestRecord.getProcessCode() != 0 && lastestRecord.getProcessCode() != 5) {
                    applicationProcessEntityMapper.save(applicationProcessEntity);
                }
                return;
            }
            // 用户没有面试失败的记录，才需要添加，并且用户已经拿到offer了不需要添加
            // 进入下一个流程，获取最新的应聘记录，在其上加1
            if (lastestRecord.getProcessCode() != 0 && lastestRecord.getProcessCode() != 5) {
                applicationProcessEntity.setProcessCode(lastestRecord.getProcessCode() + 1);
                applicationProcessEntityMapper.save(applicationProcessEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("保存失败");
        }
    }

    /**
     * 删除用户的应聘记录
     *
     * @param appId
     */
    public void delete(Long appId) {
        try {
            // 查询最新的插入记录
            ApplicationProcessEntity lastestRecord =
                    applicationProcessEntityMapper.findLastestRecord(appId);
            // appId不存在
            if (lastestRecord == null) {
                return;
            }
            // 第一条记录不能删
            if (lastestRecord.getProcessCode() != 1) {
                applicationProcessEntityMapper.delete(lastestRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败");
        }
    }
}
