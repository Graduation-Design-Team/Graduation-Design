package com.portal.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel("文章照片类")
@Entity
@Table(name = "article_picture")
public class ArticlePictureEntity {
    private Long picId;
    private Long size;
    private String url;
    private String picCreatedTime;
    private Byte isDel;
    private Byte isPass;


    public ArticlePictureEntity() {

    }

    public ArticlePictureEntity(Long size, String url, String picCreatedTime, Byte isDel, Byte isPass) {
        this.size = size;
        this.url = url;
        this.picCreatedTime = picCreatedTime;
        this.isDel = isDel;
        this.isPass = isPass;
    }

    public ArticlePictureEntity(Long picId, Long size, String url, String picCreatedTime, Byte isDel, Byte isPass) {
        this.picId = picId;
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

}
