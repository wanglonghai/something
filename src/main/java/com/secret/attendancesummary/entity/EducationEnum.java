package com.secret.attendancesummary.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 学历
 *
 * @Description
 * @Author
 * @Date 2020-11-19
 */
public enum EducationEnum {
    PRIMARY(1, "小学"),
    JUNIOR_MIDDLE(2, "初中"),
    MIDDLE(3, "高中/中专"),
    OPEN_UNIVERSITY(4, "函授/电大"),
    JUNIOR_COLLEGE(5, "大专"),
    UNDERGRADUATE(6, "本科"),
    MASTER(7, "硕士"),
    DOCTOR(8, "博士");

    @EnumValue
    private final int val;
    private final String name;

    EducationEnum(int val, String name) {
        this.val = val;
        this.name = name;
    }

    public Integer getValue() {
        return this.val;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }

    public static String getNameByVal(Integer val) {
        for (EducationEnum value : EducationEnum.values()) {
            if (value.getValue().equals(val)) {
                return value.getName();
            }
        }
        return null;
    }
}
