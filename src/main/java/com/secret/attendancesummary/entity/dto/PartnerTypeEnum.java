package com.secret.attendancesummary.entity.dto;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 合伙人类型
 * @Author ljt
 * @Date 2020-12-11
 */
public enum PartnerTypeEnum {

    NOT_PARTNER(1,"奋斗者"),
    PLATFORM_PARTNER(2,"平台合伙人"),
    COMPANY_PARTNER(3,"公司合伙人"),
    STORE_PARTNER(4,"门店合伙人");
    @EnumValue
    private  final int val;
    private  final String name;

    PartnerTypeEnum(int val, String name) {
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
        for (PartnerTypeEnum value : PartnerTypeEnum.values()) {
            if(value.getValue().equals(val)){
                return value.getName();
            }
        }
        return null;
    }
}
