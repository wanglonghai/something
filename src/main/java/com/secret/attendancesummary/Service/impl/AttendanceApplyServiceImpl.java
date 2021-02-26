package com.secret.attendancesummary.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secret.attendancesummary.Service.AttendanceApplyService;
import com.secret.attendancesummary.dao.AttendanceApplyDao;
import com.secret.attendancesummary.entity.AttendanceApply;
import com.secret.attendancesummary.entity.AttendanceApplyListVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AttendanceApplyService
 * @Author Gavin
 * @Date 2021/2/23 15:43
 * @Description 描述
 * @Version 1.0
 */
@Service
public class AttendanceApplyServiceImpl extends ServiceImpl<AttendanceApplyDao, AttendanceApply> implements AttendanceApplyService {

    @Override
    public void SaveMulti(List<AttendanceApplyListVo> attendanceApplyList) {
        List<AttendanceApply>  attendanceApplies=new ArrayList<>();
        attendanceApplyList.forEach(s->{
            attendanceApplies.add(new AttendanceApply(s));
        });
        saveBatch(attendanceApplies);
    }
}
