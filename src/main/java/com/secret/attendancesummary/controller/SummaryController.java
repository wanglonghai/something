package com.secret.attendancesummary.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secret.attendancesummary.Service.AttendanceRecordService;
import com.secret.attendancesummary.Service.AttendanceSummaryService;
import com.secret.attendancesummary.common.LayuiTableResultUtil;
import com.secret.attendancesummary.entity.AttendanceSummary;
import com.secret.attendancesummary.entity.AttendanceSummaryDto;
import com.secret.attendancesummary.job.SummaryJob;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SummaryController
 * @Author Gavin
 * @Date 2021/2/25 15:32
 * @Description 描述
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/summary")
public class SummaryController {
    @Autowired
    AttendanceSummaryService attendanceSummaryService;
    @Autowired
    SummaryJob summaryJob;
    @ResponseBody
    @PostMapping("/list")
    public LayuiTableResultUtil<List> getSummaryRecords(@RequestBody AttendanceSummaryDto attendanceSummary){
        IPage<AttendanceSummary> pageMy= new Page(attendanceSummary.getPage(),attendanceSummary.getLimit());
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(attendanceSummary.getPersonName()),"person_name",attendanceSummary.getPersonName());
        queryWrapper.in(attendanceSummary.getDepartments().size()>0,"department_name",attendanceSummary.getDepartments());
        queryWrapper.eq(attendanceSummary.getAttendanceDate()!=null,"attendance_date",attendanceSummary.getAttendanceDate());
        queryWrapper.eq(StringUtils.isNotBlank(attendanceSummary.getDepartmentName()),"department_name",attendanceSummary.getDepartmentName());
        queryWrapper.eq(StringUtils.isNotBlank(attendanceSummary.getCompanyName()),"company_name",attendanceSummary.getCompanyName());
        queryWrapper.select("person_name","attendance_date","summary_today","summary_add_status","week");
        queryWrapper.orderByDesc("attendance_date");
        IPage<AttendanceSummary> r=attendanceSummaryService.page(pageMy,queryWrapper);
        Long total=Long.valueOf(r.getTotal());
        LayuiTableResultUtil<List> list=new LayuiTableResultUtil<>("",r.getRecords(),0,total.intValue());
        return list;
    }
    @ResponseBody
    @GetMapping("/getDepartment")
    public List<String> getDepartments(){
        List<String> r=attendanceSummaryService.getColumnValues("department_name");
        return r;
    }
    @ResponseBody
    @GetMapping("/getCompany")
    public List<String> getCompanys(){
        List<String> r=attendanceSummaryService.getColumnValues("company_name");
        return r;
    }
    @ResponseBody
    @GetMapping("/doSummary/{day}")
    public String doSummary(@PathVariable(value = "day") Integer day){
        summaryJob.doSummaryData(day);
        return "成功导入"+Math.abs(day)+"天前的日志数据";
    }
    @GetMapping("/page")
    public String summaryPage(Model model){
        model.addAttribute("company",getCompanys());
        model.addAttribute("department",getDepartments());
        return "summary";
    }
    @ResponseBody
    @PostMapping("/clearRepeatSummary")
    public String clearRepeatSummary(){
        attendanceSummaryService.clearRepeatSummary();
        return "清理成功";
    }
}
