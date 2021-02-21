package com.portal.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user")
@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class User implements Serializable {

    private Integer user_id;//用户id

    private String user_name;//用户名

    private Integer user_sex;//用户性别,male为男性为1，famale为女性为2,没有填为0

    private String user_address;//用户地址

    private Integer user_age;//用户年龄

    private Date user_creatime;//用户创建时间

    private Integer user_phone;//用户联系方式

    private String user_mail;//用户邮箱

    private String user_photo_url;//用户头像地址连接

}
