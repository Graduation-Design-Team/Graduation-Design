package com.portal.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CityPosDetailEntityPK implements Serializable {
    private Long detailId;
    private Long cityId;
    private Long count;

    @Column(name = "detail_id")
    @Id
    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    @Column(name = "city_id")
    @Id
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Column(name = "count")
    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}