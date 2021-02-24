package com.secret.attendancesummary.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName 我的日志列表查询条件
 * @Author Gavin
 * @Date 2020/11/17 17:21
 * @Description 描述
 * @Version 1.0
 */
@Data
//("我的日志小程序列表查询条件类")
public class MySummaryAppletQueryDto {
    //("每页记录数")
    private Integer limit;
    //("当前页码")
    private Integer page;

    //("员工姓名,公司名称，门店名称（模糊查询）")
    private String keyWord;
    //("人员类型（0我的管理，1我的关注）")
    private Integer personType;
    //("员工ID,默认初始当前登录人员")
    private List<Long> personIds=new ArrayList<>();
    //("日期过滤条件（支持多个，数组，格式yyyy-MM-dd）")
    List<String> summaryDates=new ArrayList<>();
    //("月份过滤条件（支持多个，数组，格式yyyy-MM）")
    List<String> summaryMonths=new ArrayList<>();
}
