package com.portal.service;


import com.portal.mapper.CityEntityMapper;
import com.portal.pojo.CityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//保证事务的一致性
@Transactional
@Service
public class CityEntityService {

    @Autowired
    private CityEntityMapper cityEntityMapper;


    /**
     * 获取城市信息
     * @return
     */
    public List<CityEntity> getCityEntity() {
        List<CityEntity> list = null;
        try {
            list = cityEntityMapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
        return list;
    }

    /**
     *添加城市信息
     * @param cityEntity
     */
    public void addCityEntity(CityEntity cityEntity) {
        try {
            cityEntityMapper.save(cityEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败");
        }
    }


    /**
     * 更新城市信息
     * @param cityEntity
     */
    public void updateCityEntity(CityEntity cityEntity) {
        int isDel = cityEntity.getCityIsDel();
        if(isDel != 0 && isDel != 1) {
            cityEntity.setCityIsDel((byte)0);
        }
        try {
            cityEntityMapper.save(cityEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("保存失败");
        }
    }

    /**
     * 删除城市信息
     * @param id
     */
    public void deleteCityEntity(Long id) {
        try {
            if(cityEntityMapper.existsById(id)) {
                cityEntityMapper.deleteCityEntity(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败");
        }
    }

    /**
     * 启用城市信息
     * @param id
     */
    public void openCityEntity(Long id) {
        try {
            if(cityEntityMapper.existsById(id)) {
                cityEntityMapper.openCityEntity(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("启用失败");
        }
    }
}
