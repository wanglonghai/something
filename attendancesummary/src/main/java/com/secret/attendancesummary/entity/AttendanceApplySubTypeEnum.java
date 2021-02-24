package com.secret.attendancesummary.entity;

import com.fasterxml.jackson.annotation.JsonValue;


public enum AttendanceApplySubTypeEnum {

	CASUAL_LEAVE(11,"事假"),
	SICK_LEAVE(12,"病假"),
	MARRIAGE_LEAVE(13,"婚假"),
	MATERNITY_LEAVE(14,"产假"),
	FUNERAL_LEAVE(15,"丧假"),
	GENERAL_HOLIDAY(16,"公休"),
	ANNUAL_LEAVE(17,"年假"),
	REISSUE_CARD(21,"补卡"),
	NONE_LEAVE(10,"未知"),
	;


	private  final int val;
	private  final String name;

	AttendanceApplySubTypeEnum(int val, String name) {
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

	public static AttendanceApplySubTypeEnum getByVal(Integer val){
		for (AttendanceApplySubTypeEnum value : AttendanceApplySubTypeEnum.values()) {
			if(value.getValue().equals(val)){
				return value;
			}
		}
		return NONE_LEAVE;
	}

	public static Integer getValByName(String Name){
		for (AttendanceApplySubTypeEnum value : AttendanceApplySubTypeEnum.values()) {
			if(value.getName().equals(Name)){
				return value.getValue();
			}
		}
		return null;
	}
}
