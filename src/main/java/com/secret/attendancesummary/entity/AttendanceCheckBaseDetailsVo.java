package com.secret.attendancesummary.entity;

import com.secret.attendancesummary.common.DateUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
public class AttendanceCheckBaseDetailsVo {
	/**
	 * 详细子类型： 11事假，12病假，13婚假，14产假，15丧假，16调休
	 */
	private Integer applySubType;
	/**
	 * 详细子类型中文解析
	 */
	private String applySubTypeStr;
	/**
	 * 类型：1请假、2补卡
	 */
	private Integer applyType;
	/**
	 * 开始时间
	 */
	private Date applyStartTime;
	/**
	 * 开始时间解析
	 */
	private String applyStartTimeStr;
	/**
	 * 结束时间
	 */
	private Date applyEndTime;
	/**
	 * 结束时间解析
	 */
	private String applyEndTimeStr;
	/**
	 * 时长（单位天）
	 */
	private BigDecimal applyDuration;
	/**
	 * 申请原因
	 */
	private String applyReason;
	/**
	 * 审核状态：0：未审核、1：通过、2：不通过
	 */
	private Integer status;
	/**
	 * 图片集合
	 */
	private List<AttendanceApplyMediaListVo> applyMediaList;
	/**
	 *  请假申请开始类型  0 上午 1下午
	 */
	private Integer applyStartStatus;
	/**
	 *  请假申请结束类型  0 上午 1下午
	 */
	private Integer applyEndStatus;
	/**
	 * 申请时间
	 */
	private Date  createTime;
	/**
	 * 审核原因
	 */
	private String resultReason;

	public String getApplySubTypeStr() {
		return AttendanceApplySubTypeEnum.getByVal(this.applySubType).getName();
	}

	public String getApplyStartTimeStr() {
		String   forenoon=this.getApplyStartStatus()!=null? this.getApplyStartStatus().equals(0)?" 上午":" 下午":"";
		String result=this.getApplyStartTime()!=null? DateUtil.getFormatTimeString(this.getApplyStartTime(),"yyyy-MM-dd"):"";
		return  result+forenoon;
	}

	public String getApplyEndTimeStr() {
		String   forenoon=this.getApplyEndStatus()!=null? this.getApplyEndStatus().equals(0)?" 上午":" 下午":"";
		String result=this.getApplyEndTime()!=null?DateUtil.getFormatTimeString(this.getApplyEndTime(),"yyyy-MM-dd"):"";
		return  result+forenoon;
	}

}
