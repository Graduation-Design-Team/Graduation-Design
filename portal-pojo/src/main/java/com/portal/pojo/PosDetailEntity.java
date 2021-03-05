package com.portal.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ApiModel("职位详细信息类")
@Entity
@Table(name = "pos_detail")
public class PosDetailEntity {
    private Long detailId;
    private Long posId;
    private String detailName;
    private String detailContent;
    private Byte detailIsDel;

    private Set<CityEntity> citys = new HashSet<>();

    private Set<WorkTypeEntity> workTypes = new HashSet<>();

    private PosCategoryEntity posCategory;

    public PosDetailEntity() {

    }


    public PosDetailEntity(Long posId, String detailName, String detailContent, Byte detailIsDel) {
        this.posId = posId;
        this.detailName = detailName;
        this.detailContent = detailContent;
        this.detailIsDel = detailIsDel;
    }

    public PosDetailEntity(Long detailId, Long posId, String detailName, String detailContent, Byte detailIsDel) {
        this.detailId = detailId;
        this.posId = posId;
        this.detailName = detailName;
        this.detailContent = detailContent;
        this.detailIsDel = detailIsDel;
    }

    @ApiModelProperty("职位id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    @ApiModelProperty("职位所属的类别id")
    @Column(name = "pos_id")
    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    @ApiModelProperty("职位名字")
    @Column(name = "detail_name")
    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    @ApiModelProperty("职位内容")
    @Column(name = "detail_content")
    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    @ApiModelProperty("职位是否删除 0 没删除 1 删除")
    @Column(name = "detail_is_del")
    public Byte getDetailIsDel() {
        return detailIsDel;
    }

    public void setDetailIsDel(Byte detailIsDel) {
        this.detailIsDel = detailIsDel;
    }


    @ManyToMany(targetEntity = CityEntity.class, cascade = CascadeType.ALL)
    @JoinTable(name = "city_pos_detail",
            joinColumns = {@JoinColumn(name = "detail_id", referencedColumnName = "detail_id", insertable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "city_id", referencedColumnName = "city_id", insertable = false, updatable = false)})
    public Set<CityEntity> getCitys() {
        return citys;
    }

    public void setCitys(Set<CityEntity> citys) {
        this.citys = citys;
    }


    @ManyToMany(targetEntity = WorkTypeEntity.class, cascade = CascadeType.ALL)
    @JoinTable(name = "pos_detail_work_type",
            joinColumns = {@JoinColumn(name = "detail_id", referencedColumnName = "detail_id", insertable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "type_id", referencedColumnName = "type_id", insertable = false, updatable = false)})
    public Set<WorkTypeEntity> getWorkTypes() {
        return workTypes;
    }

    public void setWorkTypes(Set<WorkTypeEntity> workTypes) {
        this.workTypes = workTypes;
    }

    @JsonIgnore
    @ManyToOne(targetEntity = PosCategoryEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "pos_id", referencedColumnName = "pos_id", insertable = false, updatable = false)
    public PosCategoryEntity getPosCategory() {
        return posCategory;
    }

    public void setPosCategory(PosCategoryEntity posCategory) {
        this.posCategory = posCategory;
    }
}
