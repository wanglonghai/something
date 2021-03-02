package com.secret.attendancesummary.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secret.attendancesummary.Service.AttendanceSummaryService;
import com.secret.attendancesummary.Service.EmployeeService;
import com.secret.attendancesummary.common.LayuiTableResultUtil;
import com.secret.attendancesummary.entity.AttendanceSummary;
import com.secret.attendancesummary.entity.EmployeeInfo;
import com.secret.attendancesummary.entity.dto.AttendanceSummaryDto;
import com.secret.attendancesummary.entity.dto.EmployeeInfoDto;
import com.secret.attendancesummary.job.SummaryJob;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SummaryController
 * @Author Gavin
 * @Date 2021/2/25 15:32
 * @Description 描述
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @ResponseBody
    @PostMapping("/list")
    public LayuiTableResultUtil<List> getSummaryRecords(@RequestBody EmployeeInfoDto employeeInfoDto){
        IPage<EmployeeInfo> pageMy= new Page(employeeInfoDto.getPage(),employeeInfoDto.getLimit());
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(employeeInfoDto.getPerName()),"per_name",employeeInfoDto.getPerName() );
        queryWrapper.like(StringUtils.isNotBlank(employeeInfoDto.getDeptName()),"dept_name",employeeInfoDto.getDeptName() );

        queryWrapper.eq(StringUtils.isNotBlank(employeeInfoDto.getStatus()),"status",employeeInfoDto.getStatus() );
        queryWrapper.eq(StringUtils.isNotBlank(employeeInfoDto.getEducationDesc()),"education_desc",employeeInfoDto.getEducationDesc() );
        queryWrapper.eq(StringUtils.isNotBlank(employeeInfoDto.getPositionName()),"position_name",employeeInfoDto.getPositionName() );
        queryWrapper.like(StringUtils.isNotBlank(employeeInfoDto.getAddress()),"address",employeeInfoDto.getAddress() );
        queryWrapper.like(StringUtils.isNotBlank(employeeInfoDto.getGraduateSchool()),"graduate_school",employeeInfoDto.getGraduateSchool() );

        IPage<EmployeeInfo> r=employeeService.page(pageMy,queryWrapper);
        Long total=Long.valueOf(r.getTotal());
        LayuiTableResultUtil<List> list=new LayuiTableResultUtil<>("",r.getRecords(),0,total.intValue());
        return list;
    }
    @ResponseBody
    @GetMapping("/doEmployee/{day}")
    public String doEmployee(@PathVariable(value = "day") Integer day){
        Date d=null;
        if(!Integer.valueOf(88888888).equals(day)){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, day);//昨天
            d=calendar.getTime();
        }

        employeeService.doDataSchedule(d);
        return "成功导入"+Math.abs(day)+"天前入职的员工数据";
    }
    @ResponseBody
    @GetMapping("/getStatus")
    public List<String> getStatus(){
        List<String> r=employeeService.getColumnValues("status");
        return r;
    }
    @ResponseBody
    @GetMapping("/getPositionName")
    public List<String> getPositionName(){
        List<String> r=employeeService.getColumnValues("position_name");
        return r;
    }
    @ResponseBody
    @GetMapping("/getEducationDesc")
    public List<String> getEducationDesc(){
        List<String> r=employeeService.getColumnValues("education_desc");
        return r;
    }
    @GetMapping("/page")
    public String summaryPage(Model model){
        model.addAttribute("positionName",getPositionName());
        model.addAttribute("educationDesc",getEducationDesc());
        model.addAttribute("statusName",getStatus());
        return "employee";
    }
}
