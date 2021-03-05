package com.portal.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel("文档类")
@Entity
@Table(name = "document")
public class DocumentEntity {
    private Long docId;
    private Long userId;
    private String docTitle;
    private String docContent;
    private Integer docCount;
    private String docCreatedTime;
    private Byte isDel;
    private Byte isPass;


    public static String newDateTime() {
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        return datetime;
    }

    @ApiModelProperty("文档的id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id", nullable = false)
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    @ApiModelProperty("用户的id")
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @ApiModelProperty("文档的标题")
    @Column(name = "doc_title", nullable = false, length = 32)
    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    @ApiModelProperty("文档的内容")
    @Column(name = "doc_content", nullable = false, length = -1)
    public String getDocContent() {
        return docContent;
    }

    public void setDocContent(String docContent) {
        this.docContent = docContent;
    }

    @ApiModelProperty("文档点击的次数")
    @Column(name = "doc_count", nullable = false)
    public Integer getDocCount() {
        return docCount;
    }

    public void setDocCount(Integer docCount) {
        this.docCount = docCount;
    }

    @ApiModelProperty("文档创建的时间")
    @Column(name = "doc_created_time", nullable = false)
    public String getDocCreatedTime() {
        return docCreatedTime;
    }

    public void setDocCreatedTime(String docCreatedTime) {
        this.docCreatedTime = docCreatedTime;
    }

    @ApiModelProperty("是否删除 0 没删除 1 删除")
    @Column(name = "is_del")
    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    @ApiModelProperty("是否通过审核 0 通过审核 1审核中")
    @Column(name = "is_pass")
    public Byte getIsPass() {
        return isPass;
    }

    public void setIsPass(Byte isPass) {
        this.isPass = isPass;
    }
}
