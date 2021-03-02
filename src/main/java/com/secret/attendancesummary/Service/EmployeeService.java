package com.secret.attendancesummary.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.secret.attendancesummary.common.PageUtils;
import com.secret.attendancesummary.entity.AttendanceSummary;
import com.secret.attendancesummary.entity.EmployeeInfo;
import com.secret.attendancesummary.entity.MySummaryAppletQueryDto;
import com.secret.attendancesummary.entity.MySummaryAppletQueryResult;
import com.secret.attendancesummary.entity.dto.EmployeeInfoDto;

import java.util.Date;
import java.util.List;

/**
 * @ClassName AttendanceCheckService
 * @Author Gavin
 * @Date 2021/2/5 14:12
 * @Description 描述
 * @Version 1.0
 */
public interface EmployeeService extends IService<EmployeeInfo> {
    void SaveMulti(List<EmployeeInfo> summaryAppletQueryResultList);
    PageUtils<EmployeeInfo> getDataFromRemote(EmployeeInfoDto employeeInfoDto);
    //读取数据并保存
    void doDataSchedule(Date date);
    List<String> getColumnValues(String fieldName);
}
