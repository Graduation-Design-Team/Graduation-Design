package com.portal.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel("导航栏照片类")
@Entity
@Table(name = "nav_picture")
public class NavPictureEntity {
    private Long picId;
    private Long navId;
    private Long size;
    private String url;
    private String picCreatedTime;
    private Byte isDel;
    private Byte isPass;


    private NavCategoryEntity navCategoryEntity;

    public NavPictureEntity() {

    }

    public NavPictureEntity(Long navId, Long size, String url, String picCreatedTime, Byte isDel, Byte isPass) {
        this.navId = navId;
        this.size = size;
        this.url = url;
        this.picCreatedTime = picCreatedTime;
        this.isDel = isDel;
        this.isPass = isPass;
    }


    public NavPictureEntity(Long picId, Long navId, Long size, String url, String picCreatedTime, Byte isDel, Byte isPass) {
        this.picId = picId;
        this.navId = navId;
        this.size = size;
        this.url = url;
        this.picCreatedTime = picCreatedTime;
        this.isDel = isDel;
        this.isPass = isPass;
    }

    public static String newDateTime(){
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        return datetime;
    }


    @ApiModelProperty("照片的id")
    @Id
    @Column(name = "pic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long  getPicId() {
        return picId;
    }

    public void setPicId(Long  picId) {
        this.picId = picId;
    }

    @ApiModelProperty("导航栏分类的id")
    @Column(name = "nav_id")
    public Long getNavId() {
        return navId;
    }

    public void setNavId(Long navId) {
        this.navId = navId;
    }

    @ApiModelProperty("照片的大小")
    @Column(name = "size")
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @ApiModelProperty("照片的url")
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ApiModelProperty("照片创建的时间")
    @Column(name = "pic_created_time")
    public String getPicCreatedTime() {
        return picCreatedTime;
    }

    public void setPicCreatedTime(String picCreatedTime) {
        this.picCreatedTime = picCreatedTime;
    }

    @ApiModelProperty("照片是否删除")
    @Column(name = "is_del")
    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    @ApiModelProperty("照片是否通过审核")
    @Column(name = "is_pass")
    public Byte getIsPass() {
        return isPass;
    }

    public void setIsPass(Byte isPass) {
        this.isPass = isPass;
    }


    @JsonIgnore//加上这个注解，不然会导致无限递归 因为照片类中toString的时候会调用导航栏类 导航栏类toString的时候会调用照片类
    //主表和外键表的字段相同，要禁用插入和删除，不然报错insertable = false, updatable = false
    @ManyToOne(targetEntity = NavCategoryEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "nav_id", referencedColumnName = "nav_id", insertable = false, updatable = false)
    public NavCategoryEntity getNavCategoryEntity() {
        return navCategoryEntity;
    }

    public void setNavCategoryEntity(NavCategoryEntity navCategoryEntity) {
        this.navCategoryEntity = navCategoryEntity;
    }

}
