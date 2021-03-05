package com.portal.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel("用户应聘进程类")
@Entity
@Table(name = "application_process")
public class ApplicationProcessEntity {

    private Long id;
    private Long appId;
    private Long processCode;
    private String createdTime;

    public ApplicationProcessEntity() {

    }

    public ApplicationProcessEntity(Long appId, Long processCode, String createdTime) {
        this.appId = appId;
        this.processCode = processCode;
        this.createdTime = createdTime;
    }


    public static String newDateTime(){
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        return datetime;
    }

    @ApiModelProperty("主键id")
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ApiModelProperty("应聘记录Id")
    @Column(name = "app_id")
    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    @ApiModelProperty("应聘记录Id")
    @Column(name = "process_code")
    public Long getProcessCode() {
        return processCode;
    }

    public void setProcessCode(Long processCode) {
        this.processCode = processCode;
    }

    @ApiModelProperty("创建时间")
    @Column(name = "created_time")
    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

}
