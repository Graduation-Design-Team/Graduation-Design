package com.portal.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 菜系id
     */
    private Integer typeId;
    /**
     * 菜系名称
     */
    private String typeName;
}
