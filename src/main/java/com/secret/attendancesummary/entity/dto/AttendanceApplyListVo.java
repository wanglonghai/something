package com.secret.attendancesummary.entity.dto;
import com.secret.attendancesummary.entity.AttendanceApply;
import com.secret.attendancesummary.entity.AttendanceApplyStatusEnum;
import com.secret.attendancesummary.entity.AttendanceApplySubTypeEnum;
import com.secret.attendancesummary.entity.AttendanceApplyTypeEnum;
import lombok.Data;

@Data
public class AttendanceApplyListVo extends AttendanceApply {
	/**
	 * 审核类型 1请假、2补卡
	 */
	private Integer applyType;

	/**
	 * 申请详细子类型：11事假，12病假，13婚假，14产假，15丧假，16公休，21补卡
	 */
	private Integer applySubType;

	/**
	 * 审核状态：0：未审核、1：通过、2：不通过
	 */
	private Integer status;

	@Override
	public String getApplyTypeName() {
		if(applyType!=null){
				super.setApplyTypeName(AttendanceApplyTypeEnum.getByVal(applyType).getName());
		}
		return super.getApplyTypeName();
	}
	@Override
	public String getApplySubTypeName() {
		if(applySubType!=null){
			super.setApplySubTypeName(AttendanceApplySubTypeEnum.getByVal(applySubType).getName());
		}
		return super.getApplySubTypeName();
	}
	@Override
	public String getStatusName() {
		if(status!=null){
			super.setStatusName(AttendanceApplyStatusEnum.getByVal(status).getName());
		}
		return super.getStatusName();
	}
}
