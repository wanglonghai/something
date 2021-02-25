package com.secret.attendancesummary.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secret.attendancesummary.Service.AttendanceApplyService;
import com.secret.attendancesummary.Service.AttendanceCheckService;
import com.secret.attendancesummary.Service.AttendanceRecordService;
import com.secret.attendancesummary.Service.AttendanceSummaryService;
import com.secret.attendancesummary.common.LayuiTableResultUtil;
import com.secret.attendancesummary.common.PageUtils;
import com.secret.attendancesummary.common.login.TokenUtils;
import com.secret.attendancesummary.entity.*;
import com.secret.attendancesummary.job.AttendanceJob;
import com.secret.attendancesummary.job.SummaryJob;
import com.sun.org.apache.bcel.internal.generic.ATHROW;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.applet.AppletStub;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName InfoController
 * @Author Gavin
 * @Date 2021/2/23 11:49
 * @Description 描述
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/apply")
public class ApplyController {
    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    AttendanceApplyService attendanceApplyService;
    @Autowired
    AttendanceJob attendanceJob;
    @ResponseBody
    @PostMapping("/list")
    @SneakyThrows
    public LayuiTableResultUtil<List> getAttendanceRecords(@RequestBody  AttendanceApplyDto attendanceApplyDto){
        IPage<AttendanceApply> pageMy= new Page<>(attendanceApplyDto.getPage(),attendanceApplyDto.getLimit());
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq(StringUtils.isNotBlank(attendanceApplyDto.getApplyTypeName()),"apply_type_name", attendanceApplyDto.getApplyTypeName());
        queryWrapper.eq(StringUtils.isNotBlank(attendanceApplyDto.getApplySubTypeName()),"apply_sub_type_name", attendanceApplyDto.getApplySubTypeName());
        //大于等于
        queryWrapper.ge(attendanceApplyDto.getApplyDuration()!=null,"apply_duration", attendanceApplyDto.getApplyDuration());
        queryWrapper.like(StringUtils.isNotBlank(attendanceApplyDto.getApplyName()),"apply_name",attendanceApplyDto.getApplyName());
        queryWrapper.orderByDesc("create_time");
        IPage<AttendanceApply> r=attendanceApplyService.page(pageMy,queryWrapper);
        Long total=Long.valueOf(r.getTotal());
        LayuiTableResultUtil<List> list=new LayuiTableResultUtil<>("",r.getRecords(),0,total.intValue());
        return list;
    }
    @ResponseBody
    @GetMapping("/doApply/{day}")
    public String doApply(@PathVariable(value = "day") Integer day){
        attendanceJob.doApplyData(day);
        return "成功导入"+Math.abs(day)+"天前的审批数据";
    }
    @GetMapping("/page")
    public String applyPage(){
        return "apply";
    }
}
