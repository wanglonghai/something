package com.secret.attendancesummary.entity.dto;
import com.secret.attendancesummary.entity.EmployeeInfo;
import lombok.Data;

import java.util.Date;

/**
 * 用户
 */
@Data
public class EmployeeInfoDto  {
    private String perName;
    private String deptName;
    private String status;
    private String positionName;
    private String educationDesc;
    private Date minJoinDate;
    private Date maxJoinDate;
    private String address;
    private String graduateSchool;
    private Integer limit=10;
    private Integer page=1;

}
