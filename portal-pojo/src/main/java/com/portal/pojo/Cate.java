package com.portal.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * cate
 *
 * @author
 */
@Data
public class Cate implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer cateId;
    /**
     * 美食标题
     */
    private String cateTitle;
    /**
     * 美食介绍
     */
    private String cateContent;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;
    /**
     * 1:有效，0无效
     */
    private Boolean status;
    /**
     * 浏览量
     */
    private Integer readCount;
    /**
     * 标题图片
     */
    private String img;
    /**
     * 菜品id
     */
    private Integer typeId;
    /**
     * 菜品类型
     */
    private String typeName;
}