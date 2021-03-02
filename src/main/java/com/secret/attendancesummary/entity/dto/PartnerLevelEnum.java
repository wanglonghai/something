package com.secret.attendancesummary.entity.dto;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 合伙人类型
 * @Author ljt
 * @Date 2020-12-11
 */
public enum PartnerLevelEnum {

    LIFE_LONG(1,"终生合伙人"),
    SIGN(2,"签约合伙人");
    @EnumValue
    private  final int val;
    private  final String name;

    PartnerLevelEnum(int val, String name) {
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
        for (PartnerLevelEnum value : PartnerLevelEnum.values()) {
            if(value.getValue().equals(val)){
                return value.getName();
            }
        }
        return null;
    }
}
