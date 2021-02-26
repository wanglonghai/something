package com.secret.attendancesummary.entity;

import com.fasterxml.jackson.annotation.JsonValue;


public enum AttendanceApplyTypeEnum {

	QJ(1,"请假"),
	BK(2,"补卡"),
	WZ(-1,"未知"),
	;


	private  final int val;
	private  final String name;

	AttendanceApplyTypeEnum(int val, String name) {
		this.val = val;
		this.name = name;
	}

	public Integer getValue() {
		return this.val;
	}

	@JsonValue
	public String getName(){
		return this.name;
	}

	public static AttendanceApplyTypeEnum getByVal(Integer val){
		for (AttendanceApplyTypeEnum value : AttendanceApplyTypeEnum.values()) {
			if(value.getValue().equals(val)){
				return value;
			}
		}
		return WZ;
	}

	public static Integer getValByName(String Name){
		for (AttendanceApplyTypeEnum value : AttendanceApplyTypeEnum.values()) {
			if(value.getName().equals(Name)){
				return value.getValue();
			}
		}
		return null;
	}
}
