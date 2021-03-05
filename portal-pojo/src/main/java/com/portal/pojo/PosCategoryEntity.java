package com.portal.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ApiModel("职位分类实体类")
@Entity
@Table(name = "pos_category")
public class PosCategoryEntity {
    private Long posId;
    private String posName;
    private Integer posParentId;
    private Byte posIsParent;
    private Byte posIsDel;
    private Integer posSort;

    private Set<PosDetailEntity> posDetails = new HashSet<>();


    @ApiModelProperty("职位分类id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pos_id", nullable = false)
    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    @ApiModelProperty("职位分类名字")
    @Column(name = "pos_name")
    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    @ApiModelProperty("职位父分类id")
    @Column(name = "pos_parent_id")
    public Integer getPosParentId() {
        return posParentId;
    }

    public void setPosParentId(Integer posParentId) {
        this.posParentId = posParentId;
    }

    @ApiModelProperty("是否是父分类")
    @Column(name = "pos_is_parent")
    public Byte getPosIsParent() {
        return posIsParent;
    }

    public void setPosIsParent(Byte posIsParent) {
        this.posIsParent = posIsParent;
    }

    @ApiModelProperty("是否删除 0 没删除 1 删除")
    @Column(name = "pos_is_del")
    public Byte getPosIsDel() {
        return posIsDel;
    }

    public void setPosIsDel(Byte posIsDel) {
        this.posIsDel = posIsDel;
    }


    @ApiModelProperty("排序")
    @Column(name = "pos_sort")
    public Integer getPosSort() {
        return posSort;
    }

    public void setPosSort(Integer posSort) {
        this.posSort = posSort;
    }

    @OneToMany(mappedBy = "posCategory", cascade = CascadeType.ALL)
    public Set<PosDetailEntity> getPosDetails() {
        return posDetails;
    }

    public void setPosDetails(Set<PosDetailEntity> posDetails) {
        this.posDetails = posDetails;
    }

}
