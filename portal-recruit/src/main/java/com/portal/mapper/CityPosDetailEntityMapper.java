package com.portal.mapper;

import com.portal.pojo.CityPosDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/** 城市信息和职位信息中间表接口 */
@Repository
public interface CityPosDetailEntityMapper
    extends JpaRepository<CityPosDetailEntity, Long>,
        JpaSpecificationExecutor<CityPosDetailEntity> {

    @Transactional
    @Modifying
    @Query(value = "update city_pos_detail set count = ?3 where detail_id = ?1 and city_id = ?2",nativeQuery = true)
    void updateCityPosDetail(Long detailId,Long cityId,Long count);
}
