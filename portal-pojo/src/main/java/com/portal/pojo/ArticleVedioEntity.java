package com.portal.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@ApiModel("文章视频类")
@Entity
@Table(name = "article_vedio")
public class ArticleVedioEntity {
    private Long vedioId;
    private Long articleId;
    private Long size;
    private String url;
    private String vedioCreatedTime;
    private Byte isDel;
    private Byte isPass;


    private ArticleEntity articleEntity;

    public ArticleVedioEntity() {

    }

    public ArticleVedioEntity(Long articleId, Long size, String url, String vedioCreatedTime, Byte isDel, Byte isPass) {
        this.articleId = articleId;
        this.size = size;
        this.url = url;
        this.vedioCreatedTime = vedioCreatedTime;
        this.isDel = isDel;
        this.isPass = isPass;
    }





    public static String newDateTime(){
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        return datetime;
    }



    @ApiModelProperty("视频的id")
    @Id
    @Column(name = "vedio_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getVedioId() {
        return vedioId;
    }

    public void setVedioId(Long vedioId) {
        this.vedioId = vedioId;
    }

    @ApiModelProperty("文章的id")
    @Column(name = "article_id")
    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @ApiModelProperty("视频的大小")
    @Column(name = "size")
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @ApiModelProperty("视频的url")
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @ApiModelProperty("视频创建的时间")
    @Column(name = "vedio_created_time")
    public String getVedioCreatedTime() {
        return vedioCreatedTime;
    }

    public void setVedioCreatedTime(String vedioCreatedTime) {
        this.vedioCreatedTime = vedioCreatedTime;
    }

    @ApiModelProperty("视频是否删除")
    @Column(name = "is_del")
    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    @ApiModelProperty("视频是否通过审核")
    @Column(name = "is_pass")
    public Byte getIsPass() {
        return isPass;
    }

    public void setIsPass(Byte isPass) {
        this.isPass = isPass;
    }


    @JsonIgnore
    @ManyToOne(targetEntity = ArticleEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", referencedColumnName = "article_id", insertable = false, updatable = false)
    public ArticleEntity getArticleEntity() {
        return articleEntity;
    }

    public void setArticleEntity(ArticleEntity articleEntity) {
        this.articleEntity = articleEntity;
    }
}
