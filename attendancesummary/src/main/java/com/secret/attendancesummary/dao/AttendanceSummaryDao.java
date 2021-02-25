package com.secret.attendancesummary.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secret.attendancesummary.entity.AttendanceApply;
import com.secret.attendancesummary.entity.AttendanceSummary;
import org.apache.ibatis.annotations.Delete;
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
public interface AttendanceSummaryDao extends BaseMapper<AttendanceSummary> {
    @Delete(value = "delete from summary_list where \n" +
            "id in (select id from summary_list where  ifnull(attendance_date,0)+account_id in (select  ifnull(attendance_date,0)+account_id from summary_list group by attendance_date,account_id  having count(1)>1))\n" +
            "and id not in (select  max(id) from summary_list group by attendance_date,account_id  having count(1)>1)")
    void clearRepeatSummary();

    @Select("select distinct(${fieldName}) from summary_list")
    List<String> getColumnValues(String fieldName);
}
