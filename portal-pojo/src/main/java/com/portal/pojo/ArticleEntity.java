package com.portal.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@ApiModel("文章类")
@Entity
@Table(name = "article")
public class ArticleEntity {
    private Long articleId;
    private Long userId;
    private String content;
    private String articleCreatedTime;
    private Byte isDel;
    private Byte isPass;

    private Set<ArticleVedioEntity> vedio = new HashSet<>();

    private UserEntity user;

    public static String newDateTime() {
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        return datetime;
    }


    @ApiModelProperty("文章id")
    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @ApiModelProperty("用户id")
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @ApiModelProperty("文章内容")
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty("文章创建时间")
    @Column(name = "article_created_time")
    public String getArticleCreatedTime() {
        return articleCreatedTime;
    }

    public void setArticleCreatedTime(String articleCreatedTime) {
        this.articleCreatedTime = articleCreatedTime;
    }

    @ApiModelProperty("文章是否删除 0没删除 1删除")
    @Column(name = "is_del")
    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    @ApiModelProperty("文章是否审核通过 0审核通过 1审核通过")
    @Column(name = "is_pass")
    public Byte getIsPass() {
        return isPass;
    }

    public void setIsPass(Byte isPass) {
        this.isPass = isPass;
    }

    @OneToMany(mappedBy = "articleEntity", cascade = CascadeType.ALL)
    public Set<ArticleVedioEntity> getVedio() {
        return vedio;
    }

    public void setVedio(Set<ArticleVedioEntity> vedio) {
        this.vedio = vedio;
    }


    @OneToOne(targetEntity = UserEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    public UserEntity getUserEntity() {
        return user;
    }

    public void setUserEntity(UserEntity user) {
        this.user = user;
    }
}
