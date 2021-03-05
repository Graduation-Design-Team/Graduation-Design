package com.portal.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 中间表要实现serializable接口
 */
public class PosDetailWorkTypeEntityPK implements Serializable {
    private Long typeId;
    private Long detailId;

    public PosDetailWorkTypeEntityPK() {

    }

    public PosDetailWorkTypeEntityPK(Long typeId, Long detailId) {
        this.typeId = typeId;
        this.detailId = detailId;
    }

    @Column(name = "type_id")
    @Id
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Column(name = "detail_id")
    @Id
    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }
}