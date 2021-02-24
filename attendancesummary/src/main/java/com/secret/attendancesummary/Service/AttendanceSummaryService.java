package com.secret.attendancesummary.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.secret.attendancesummary.common.PageUtils;
import com.secret.attendancesummary.entity.*;

import java.util.List;

/**
 * @ClassName AttendanceCheckService
 * @Author Gavin
 * @Date 2021/2/5 14:12
 * @Description 描述
 * @Version 1.0
 */
public interface AttendanceSummaryService extends IService<AttendanceSummary> {
    void SaveMulti(List<MySummaryAppletQueryResult> summaryAppletQueryResultList);
    List<MySummaryAppletQueryResult> getSummaryList(MySummaryAppletQueryDto attendanceApplyListQuery);
    List<MySummaryAppletQueryResult> makeDataFromHttp(MySummaryAppletQueryDto attendanceApplyListQuery);
}
