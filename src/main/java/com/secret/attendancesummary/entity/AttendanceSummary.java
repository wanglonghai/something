package com.secret.attendancesummary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


@Data
@TableName("summary_list")
public class AttendanceSummary {
	public static final String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
	/**
	 * 日志id
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	//("日志表id")
	private Integer summaryId;
	//("星期")
	private String week;
	//("日志日期，格式yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")//设置日期返回格式
	private Date attendanceDate;
	//("日志提交人id")
	private Long accountId;
	//("姓名")
	private String personName;
	//("头像地址")
	private String personImageUrl;
	//("门店名称")
	private String departmentName;
	//("上级部门名称")
	private String parentDepartmentName;
	//("公司名称")
	private String companyName;
	//("考勤状态（0正常考勤、1迟到、2早退、3旷工、4请假、5、迟到早退）")
	private Integer checkInResult;
	//("自评分")
	private Integer selfScore;
	//("点评分")
	private Integer checkScore;
	//("今日总结")
	private String summaryToday;
	//("日志提交状态（0正常，-1未提交日志，-2无需提交）")
	private Integer summaryAddStatus;
	//("日志批阅状态（0待批阅（有效时间内），1已批阅，-1未批阅（超过一定时间）,-2不需要批阅）")
	private Integer summaryCheckStatus;
	/**
	 * 记录创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date recordAddTime;
	public AttendanceSummary() {
	}

	public AttendanceSummary(MySummaryAppletQueryResult s) {
		BeanUtils.copyProperties(s,this);
		this.recordAddTime=new Date();
	}

	public String getWeek() {
		if(this.attendanceDate!=null&& StringUtils.isEmpty(week)){
			Calendar cal = Calendar.getInstance();
			cal.setTime(this.attendanceDate);
			int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if(week_index<0){
				week_index = 0;
			}
			week= weeks[week_index];
		}
		return week;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttendanceSummary that = (AttendanceSummary) o;
		return Objects.equals(attendanceDate, that.attendanceDate) &&
				Objects.equals(accountId, that.accountId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(attendanceDate, accountId);
	}
}
