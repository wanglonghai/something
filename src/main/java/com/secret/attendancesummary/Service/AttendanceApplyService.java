package com.secret.attendancesummary.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.secret.attendancesummary.entity.AttendanceApply;
import com.secret.attendancesummary.entity.dto.AttendanceApplyListVo;

import java.util.List;

/**
 * @ClassName AttendanceApplyService
 * @Author Gavin
 * @Date 2021/2/23 15:43
 * @Description 描述
 * @Version 1.0
 */
public interface AttendanceApplyService extends IService<AttendanceApply> {
    void SaveMulti(List<AttendanceApplyListVo> attendanceApplyList);

}
