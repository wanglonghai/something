package com.secret.attendancesummary.entity;

import com.fasterxml.jackson.annotation.JsonValue;


public enum AttendanceApplyStatusEnum {
	WSH(0,"未审核"),
	TG(1,"通过"),
	BTG(2,"不通过"),
	WZ(-1,"未知"),
	;


	private  final int val;
	private  final String name;

	AttendanceApplyStatusEnum(int val, String name) {
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

	public static AttendanceApplyStatusEnum getByVal(Integer val){
		for (AttendanceApplyStatusEnum value : AttendanceApplyStatusEnum.values()) {
			if(value.getValue().equals(val)){
				return value;
			}
		}
		return WZ;
	}

	public static Integer getValByName(String Name){
		for (AttendanceApplyStatusEnum value : AttendanceApplyStatusEnum.values()) {
			if(value.getName().equals(Name)){
				return value.getValue();
			}
		}
		return null;
	}
}
