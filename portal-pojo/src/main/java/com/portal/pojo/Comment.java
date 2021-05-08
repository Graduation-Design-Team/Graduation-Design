package com.portal.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * comment
 *
 * @author
 */
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 评论id
     */
    private Integer commentId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 新闻id
     */
    private Integer newsId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifiedTime;
    /**
     * 1:有效，0无效
     */
    private Integer status;

    private String userName;


}