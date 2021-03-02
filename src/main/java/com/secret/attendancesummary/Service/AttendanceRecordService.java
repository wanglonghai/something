package com.secret.attendancesummary.Service;
import com.secret.attendancesummary.common.PageUtils;
import com.secret.attendancesummary.entity.AttendanceApplyListQuery;
import com.secret.attendancesummary.entity.dto.AttendanceApplyListVo;
import com.secret.attendancesummary.entity.dto.AttendanceCheckBaseDetailsVo;

import java.util.List;

/**
 * @ClassName AttendanceCheckService
 * @Author Gavin
 * @Date 2021/2/5 14:12
 * @Description 描述
 * @Version 1.0
 */
public interface AttendanceRecordService {
    PageUtils<AttendanceApplyListVo> getAttendanceApplyList(AttendanceApplyListQuery attendanceApplyListQuery);
    AttendanceCheckBaseDetailsVo getApplyDetails(Integer applyid);
    List<AttendanceApplyListVo> makeDataFromHttp(AttendanceApplyListQuery attendanceApplyListQuery);
}
