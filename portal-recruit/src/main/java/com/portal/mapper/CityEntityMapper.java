package com.portal.mapper;

import com.portal.pojo.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * 城市信息类
 */
@Repository
public interface CityEntityMapper
    extends JpaRepository<CityEntity, Long>, JpaSpecificationExecutor<CityEntity> {

    /**
     * 根据id删除城市信息
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update CityEntity set cityIsDel = 1 where cityId = ?1")
    void deleteCityEntity(Long id);

    /**
     * 根据id启用城市信息
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update CityEntity set cityIsDel = 0 where cityId = ?1")
    void openCityEntity(Long id);
}
