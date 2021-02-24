package com.secret.attendancesummary.entity;

import com.secret.attendancesummary.Service.AttendanceCheckService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @ClassName UserInfo
 * @Author Gavin
 * @Date 2021/2/8 10:13
 * @Description 描述
 * @Version 1.0
 */
@Data
public class UserInfo {

    //60分钟
    public static final Integer expireTime=60*60*1000;
    @Autowired
    AttendanceCheckService attendanceCheckService;
    private Long accountId;
    private String name;
    private String pwd;
    private String tk;
    private String openId;
    private  String message;
    //获取token的时间
    private Date tokenTime;
    //查看日志
    private Boolean isSummary;

    public void setTk(String tk) {
        this.tk = tk;
        this.tokenTime=new Date();
    }
}
