package com.portal.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * null
 *
 * @TableName user
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户主键
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 真实姓名
     */
    private String userRealName;
    /**
     * 性别
     */
    private String userSex;
    /**
     * 用户地址
     */
    private String userAddress;
    /**
     * 用户年龄
     */
    private Integer userAge;
    /**
     * 创建时间
     */
    private Date userCreatime;
    /**
     * 用户注册手机号
     */
    private String userPhone;
    /**
     * 账户密码
     */
    private String userPassword;
    /**
     * 邮箱
     */
    private String userEmail;
    /**
     * 学校信息
     */
    private String userSchool;
    /**
     * 出生日期
     */
    private Date userBirthday;
    /**
     * 学历信息
     */
    private String userEducational;
    /**
     * 用户头像地址
     */
    private String userPhotoUrl;
    /**
     * 盐
     */
    private String salt;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 是否可用
     */
    private Boolean active;

}