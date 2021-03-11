package com.secret.attendancesummary.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secret.attendancesummary.Service.AttendanceApplyService;
import com.secret.attendancesummary.common.LayuiTableResultUtil;
import com.secret.attendancesummary.common.login.TokenUtils;
import com.secret.attendancesummary.entity.AttendanceApply;
import com.secret.attendancesummary.entity.dto.AttendanceApplyDto;
import com.secret.attendancesummary.job.AttendanceJob;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName InfoController
 * @Author Gavin
 * @Date 2021/2/23 11:49
 * @Description 描述
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/manage")
public class ManageController {
    @GetMapping("/sync")
    public String applyPage(){
        return "sys/manage";
    }
}
