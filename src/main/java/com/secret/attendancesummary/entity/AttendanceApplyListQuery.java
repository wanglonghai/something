package com.secret.attendancesummary.entity;
import lombok.Data;

import java.util.Date;


@Data
public class AttendanceApplyListQuery {

	/**
	 * 申请人id
	 */
	private Integer applyId;
	/**
	 * 申请人公司id
	 */
	private Integer companyId;
	/**
	 * 申请人区域id
	 */
	private Integer areaId;
	/**
	 * 申请人部门id
	 */
	private Integer departmentId;
	/**
	 * 申请类型
	 */
	private Integer applyType;
	/**
	 * 申请子类型
	 */
	private Integer applySubType;
	/**
	 * 审核状态
	 */
	private Integer status;
	/**
	 * 申请开始时间
	 */
	private Date applyStartTime;
	/**
	 * 申请结束时间
	 */
	private Date applyEndTime;
	/**
	 * 每页的条数
	 */
	private Integer limit = 10;
	/**
	 * 第几页
	 */
	private Integer page = 1;
	/**
	 * 岗位id
	 */
	private Integer positionId;


}
