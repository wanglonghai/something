package com.secret.attendancesummary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;


@Data
@TableName("apply_list")
public class AttendanceApply {
	/**
	 * 审核id
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 申请人姓名
	 */
	private String applyName;
	/**
	 * 审核类型 1请假、2补卡
	 */
	private String applyTypeName;

	/**
	 * 申请详细子类型：11事假，12病假，13婚假，14产假，15丧假，16公休，21补卡
	 */
	private String applySubTypeName;

	/**
	 * 补卡的异常类型 1迟到、2早退、3旷工、4请假、5、迟到早退
	 * 格式：(时间 + $ + 类型；例如：2020-12-08 14:28:00$3)
	 */
	private String reissueSubType;

	/**
	 * 时长(单位天)
	 */
	private BigDecimal applyDuration;
	/**
	 * 申请时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 审核状态：0：未审核、1：通过、2：不通过
	 */
	private String statusName;
	/**
	 * 审批人姓名
	 */
	private String auditorName;
	//
	private String applyReason;
	//
	private String applyMedia="";
	/**
	 * 结束时间解析
	 */
	private String applyEndTimeStr;
	/**
	 * 开始时间解析
	 */
	private String applyStartTimeStr;
	/**
	 * 记录创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date recordAddTime;
	public AttendanceApply() {
	}
	public AttendanceApply(AttendanceApplyListVo s) {
		BeanUtils.copyProperties(s,this);
		this.id=null;
	}
}
