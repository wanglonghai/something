package com.secret.attendancesummary.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secret.attendancesummary.entity.AttendanceApply;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName AttendanceApplyDao
 * @Author Gavin
 * @Date 2021/2/23 15:42
 * @Description 描述
 * @Version 1.0
 */
@Mapper
public interface AttendanceApplyDao extends BaseMapper<AttendanceApply> {
}
