package com.portal.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * news
 *
 * @author
 */
@Data
public class News implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 新闻id
     */
    private Integer newsId;
    /**
     * 新闻标题
     */
    private String newsTitle;
    /**
     * 新闻正文
     */
    private String newsContent;
    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    /**
     * 最后编辑时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;
    /**
     * 1:有效,0:无效
     */
    private Integer status;
    /**
     * 点击量
     */
    private Integer readCount;
    /**
     * 图片地址
     */
    private String img;
}