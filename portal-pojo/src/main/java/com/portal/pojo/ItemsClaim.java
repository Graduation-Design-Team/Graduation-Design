package com.portal.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * items_claim
 * @author 
 */
@Data
public class ItemsClaim implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 拾得物品id
     */
    private Integer itemId;
    /**
     * 物品名称
     */
    private String itemName;
    /**
     * 拾得地点
     */
    private String placeLost;
    /**
     * 拾得时间
     */
    private String timeLost;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timePublished;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 1:有效,0:无效
     */
    private Boolean status;


}