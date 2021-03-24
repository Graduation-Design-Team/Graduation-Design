package com.portal.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * items_search
 * @author 
 */
@Data
public class ItemsSearch implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 失物id
     */
    private Integer itemId;
    /**
     * 失物的名称
     */
    private String itemName;
    /**
     * 丢失地点
     */
    private String placeLost;
    /**
     * 丢失时间
     */
    private String timeLost;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 该条寻物信息的发布时间
     */
    private Date timePublished;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 状态:1,有效,状态:0,无效
     */
    private Integer status;
}