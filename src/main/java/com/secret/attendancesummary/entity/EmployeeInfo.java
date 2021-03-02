package com.secret.attendancesummary.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.secret.attendancesummary.common.DateUtil;
import com.secret.attendancesummary.entity.dto.EmployeeStatusEnum;
import lombok.Data;

import java.util.Date;

/**
 * 用户
 */
@Data
@TableName("employee_info")
public class EmployeeInfo extends EmployeeDetail {
    /**
     * 账号id
     */

    private Integer accountId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 岗位名称
     */
    private String positionName;
    /**
     * 职级名称
     */
    private String levelName;
    /**
     * 状态,EmployeeStatusEnum
     */
    private String status;
    /**
     * 无效标记,EmployeeDelEnum
     */
    private String del;

    /**
     * 离职时间
     */
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date leaveTime;

    /**
     * 入职时间
     */
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regTime;

    /**
     * 有效介绍数
     */
    private Integer inviteCnt;

}
