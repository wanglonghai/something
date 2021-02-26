package com.secret.attendancesummary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.util.*;


@Data
public class AttendanceSummaryDto extends AttendanceSummary {
	List<String> departments=new ArrayList<>();
	private Integer page;
	private Integer limit;
}
