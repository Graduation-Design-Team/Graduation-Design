package com.portal.pojo;

import javax.persistence.*;

/**
 * 职位详细信息和工作类型中间类
 */
@Entity
@Table(name = "pos_detail_work_type")
@IdClass(PosDetailWorkTypeEntityPK.class)
public class PosDetailWorkTypeEntity {
    private Long typeId;
    private Long detailId;

    public PosDetailWorkTypeEntity() {

    }

    public PosDetailWorkTypeEntity(Long typeId, Long detailId) {
        this.typeId = typeId;
        this.detailId = detailId;
    }

    @Id
    @Column(name = "type_id")
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Id
    @Column(name = "detail_id")
    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }
}
