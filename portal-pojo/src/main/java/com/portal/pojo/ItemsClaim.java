package com.portal.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * items_claim
 * @author 
 */
@Data
public class ItemsClaim implements Serializable {
    /**
     * 物品的id
     */
    private Integer itemId;

    /**
     * 拾到的物品名称
     */
    private String itemName;

    /**
     * 拾到物品时的时间
     */
    private String timePicked;

    /**
     * 拾到物品的地点
     */
    private String placePicked;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 发布招领信息的时间
     */
    private Date timePubished;

    private Integer userId;

    private static final long serialVersionUID = 1L;
}