package com.portal.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@ApiModel("城市信息类")
@Entity
@Table(name = "city")
public class CityEntity {
    private Long cityId;
    private String cityName;
    private Byte cityIsDel;

    private Set<PosDetailEntity> posDetails = new HashSet<>();

    @ApiModelProperty("城市的id")
    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @ApiModelProperty("城市的名称")
    @Column(name = "city_name")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @ApiModelProperty("是否删除城市 0 没删除 1 删除")
    @Column(name = "city_is_del")
    public Byte getCityIsDel() {
        return cityIsDel;
    }

    public void setCityIsDel(Byte cityIsDel) {
        this.cityIsDel = cityIsDel;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "citys", fetch = FetchType.LAZY)
    public Set<PosDetailEntity> getPosDetails() {
        return posDetails;
    }

    public void setPosDetails(Set<PosDetailEntity> posDetails) {
        this.posDetails = posDetails;
    }
}
