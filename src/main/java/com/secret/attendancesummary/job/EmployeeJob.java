package com.secret.attendancesummary.job;
import com.alibaba.fastjson.JSON;
import com.secret.attendancesummary.Service.AttendanceSummaryService;
import com.secret.attendancesummary.Service.EmployeeService;
import com.secret.attendancesummary.entity.MySummaryAppletQueryDto;
import com.secret.attendancesummary.entity.MySummaryAppletQueryResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AttendanceJob
 * @Author Gavin
 * @Date 2021/2/23 10:05
 * @Description 描述
 * @Version 1.0
 */
@Component
@Slf4j
public class EmployeeJob {
    @Autowired
    EmployeeService employeeService;
    /**
     *
     */
    @Scheduled(cron = "59 59 5 * * ?")
    @SneakyThrows
    public void doEmployeeData() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);//昨天
        Date d=calendar.getTime();
        employeeService.doDataSchedule(d);
    }
}
