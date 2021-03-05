package com.portal.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel("招聘记录类")
@Entity
@Table(name = "application")
public class ApplicationEntity {

    private Long appId;
    private Long userId;
    private Long detailId;
    private String createdTime;
    private Byte isDel;

    public ApplicationEntity() {

    }

    public ApplicationEntity(Long userId, Long detailId, String createdTime, Byte isDel) {
        this.userId = userId;
        this.detailId = detailId;
        this.createdTime = createdTime;
        this.isDel = isDel;
    }

    public static String newDateTime(){
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        return datetime;
    }

    @ApiModelProperty("应聘记录id")
    @Id
    @Column(name = "app_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    @ApiModelProperty("用户id")
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @ApiModelProperty("职位id")
    @Column(name = "detail_id")
    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }


    @ApiModelProperty("创建时间")
    @Column(name = "created_time")
    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }


    @ApiModelProperty("是否删除")
    @Column(name = "is_del")
    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }
}
