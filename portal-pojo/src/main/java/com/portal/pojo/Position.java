package com.portal.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "position")
@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class Position implements Serializable {

    private Integer position_id;//职位id

    private String technical_position;//技术岗位

    private String company;//所属公司

    private String technical_require;//技术要求

    private Integer position_type;//招聘类型,0为没有，1表示为实习位置，2表示校招，3表示社招

    private String work_salary;//工作资薪

    private String position_location;//所在位置

    private Date release_time;//发布时间


}
