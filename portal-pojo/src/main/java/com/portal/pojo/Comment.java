package com.portal.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "comment")
@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class Comment implements Serializable {

    private Integer comment_id;

    private String comment_description;

    private String comment_pho_url;

    private String position_comment;

    private String comment_outside_url;

    private Integer comment_score;

    private Date  comment_creatime;




    private String technical_position;//技术岗位
    private String position_location;//所在位置

    private String user_name;//用户名
    private String user_mail;//用户邮箱
    private String user_photo_url;//用户头像地址连接


}
