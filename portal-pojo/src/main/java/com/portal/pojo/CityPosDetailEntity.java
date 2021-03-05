package com.portal.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel("城市信息和职位详细信息中间表类")
@Entity
@Table(name = "city_pos_detail")
//多个字段为主键必须添加@IdClass
@IdClass(CityPosDetailEntityPK.class)
public class CityPosDetailEntity {
    private Long detailId;
    private Long cityId;
    private Long count;

    public CityPosDetailEntity() {

    }

    public CityPosDetailEntity(Long detailId, Long cityId, Long count) {
        this.detailId = detailId;
        this.cityId = cityId;
        this.count = count;
    }

    @ApiModelProperty("职位的id")
    @Id
    @Column(name = "detail_id")
    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    @ApiModelProperty("城市的id")
    @Id
    @Column(name = "city_id")
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @ApiModelProperty("岗位的数量")
    @Column(name = "count")
    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}