package com.secret.attendancesummary.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * hrEmployeeInfo del 状态枚举
* @Description
* @Author ljt
* @Date 2020-11-19
*/
public enum EmployeeDelEnum {
    ON_JOB(0,"在职"),
    LEAVE_JOB(1,"离职"),
    ON_CHECK(2,"离职未审核"),
    ACCOUNT_LOCK(3,"账号锁定");
    @EnumValue
    private  final int val;
    private  final String name;

    EmployeeDelEnum(int val, String name) {
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

    public static String getNameByVal(Integer val){
        for (EmployeeDelEnum value : EmployeeDelEnum.values()) {
            if(value.getValue().equals(val)){
                return value.getName();
            }
        }
        return null;
    }
}
