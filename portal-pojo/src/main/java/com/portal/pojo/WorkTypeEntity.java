package com.portal.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ApiModel("职位类型类")
@Entity
@Table(name = "work_type")
public class WorkTypeEntity {
    private Long typeId;
    private String typeName;
    private Byte isDel;

    private Set<PosDetailEntity> posDetails = new HashSet<>();

    @ApiModelProperty("职位类型Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @ApiModelProperty("职位类型名字")
    @Column(name = "type_name")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @ApiModelProperty("是否删除 0 没删除 1 删除")
    @Column(name = "is_del")
    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "workTypes", fetch = FetchType.LAZY)
    public Set<PosDetailEntity> getPosDetails() {
        return posDetails;
    }

    public void setPosDetails(Set<PosDetailEntity> posDetails) {
        this.posDetails = posDetails;
    }
}
