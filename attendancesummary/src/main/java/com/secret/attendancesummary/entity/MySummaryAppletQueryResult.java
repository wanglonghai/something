package com.secret.attendancesummary.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName 我的日志小程序列表查询结果实体类
 * @Author Gavin
 * @Date 2020/11/17
 * @Description 描述
 * @Version 1.0
 */
@Data
//("我的日志小程序列表查询结果实体类")
public class MySummaryAppletQueryResult extends AttendanceSummary {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceSummary that = (AttendanceSummary) o;
        return Objects.equals(super.getAttendanceDate(), that.getAttendanceDate()) &&
                Objects.equals(super.getAccountId(), that.getAccountId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getAttendanceDate(), super.getAccountId());
    }
}
