package com.portal.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "resume")
@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class Resume implements Serializable {

    private Integer resume_id;//简历id

    private String work_condition;//工作情况

    private String recruit_progress;//招聘进度

    private String program_experience;//项目经验

    private String eduction_experience;//教育经历

    private String school_performance;//在校情况

    private String self_evaluation;//自我评价

    private String additional_info;//附加信息
}
