package com.portal.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel("导航栏视频类")
@Entity
@Table(name = "nav_vedio")
public class NavVedioEntity {
    private Long vedioId;
    private Long navId;
    private Long size;
    private String url;
    private String vedioCreatedTime;
    private Byte isDel;
    private Byte isPass;

    private NavCategoryEntity navCategoryEntity;


    public NavVedioEntity() {

    }

    public NavVedioEntity(Long navId, Long size, String url, String vedioCreatedTime, Byte isDel, Byte isPass) {
        this.navId = navId;
        this.size = size;
        this.url = url;
        this.vedioCreatedTime = vedioCreatedTime;
        this.isDel = isDel;
        this.isPass = isPass;
    }

    public NavVedioEntity(Long vedioId, Long navId, Long size, String url, String vedioCreatedTime, Byte isDel, Byte isPass) {
        this.vedioId = vedioId;
        this.navId = navId;
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


    @ApiModelProperty("导航栏分类的id")
    @Column(name = "nav_id")
    public Long getNavId() {
        return navId;
    }

    public void setNavId(Long navId) {
        this.navId = navId;
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
    @ManyToOne(targetEntity = NavCategoryEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "nav_id", referencedColumnName = "nav_id", insertable = false, updatable = false)
    public NavCategoryEntity getNavCategoryEntity() {
        return navCategoryEntity;
    }

    public void setNavCategoryEntity(NavCategoryEntity navCategoryEntity) {
        this.navCategoryEntity = navCategoryEntity;
    }
}
