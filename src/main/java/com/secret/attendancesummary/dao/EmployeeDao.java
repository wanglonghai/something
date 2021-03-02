package com.secret.attendancesummary.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secret.attendancesummary.entity.AttendanceApply;
import com.secret.attendancesummary.entity.EmployeeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName AttendanceApplyDao
 * @Author Gavin
 * @Date 2021/2/23 15:42
 * @Description 描述
 * @Version 1.0
 */
@Mapper
public interface EmployeeDao extends BaseMapper<EmployeeInfo> {
    @Select("select distinct(${fieldName}) from employee_info")
    List<String> getColumnValues(String fieldName);
}
