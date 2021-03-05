package com.portal.service;

import com.portal.mapper.WorkTypeEntityMapper;
import com.portal.pojo.WorkTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//保证事务的一致性
@Transactional
@Service
public class WorkTypeEntityService {

    @Autowired
    private WorkTypeEntityMapper workTypeEntityMapper;

    /**
     * 获取职位类型
     * @return
     */
    public List<WorkTypeEntity> getWorkType() {

        List<WorkTypeEntity> list = null;
        try {
            list = workTypeEntityMapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
        return list;
    }

    /**
     * 添加职位类型
     * @param workTypeEntity
     */
    public void addWorkType(WorkTypeEntity workTypeEntity) {
        try {
            workTypeEntityMapper.save(workTypeEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败");
        }
    }


    /**
     * 更新职位类型
     * @param workTypeEntity
     */
    public void updateWorkType(WorkTypeEntity workTypeEntity) {
        try {
            int isDel = workTypeEntity.getIsDel();
            if(isDel != 0 && isDel != 1) {
                workTypeEntity.setIsDel((byte) 0);
            }
            workTypeEntityMapper.save(workTypeEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新失败");
        }
    }

    /**
     * 删除职位类型
     * @param id
     */
    public void deleteWorkType(Long id) {
        try {
            if(workTypeEntityMapper.existsById(id)) {
                workTypeEntityMapper.deleteWorkType(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败");
        }
    }

    /**
     * 启用职位类型
     * @param id
     */
    public void openWorkType(Long id) {
        try {
            if(workTypeEntityMapper.existsById(id)) {
                workTypeEntityMapper.openWorkType(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("启用失败");
        }
    }
}
