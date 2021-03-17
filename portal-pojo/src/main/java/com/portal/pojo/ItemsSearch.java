package com.portal.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * items_search
 * @author 
 */
@Data
public class ItemsSearch implements Serializable {
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

    private Integer userId;

    private static final long serialVersionUID = 1L;
}