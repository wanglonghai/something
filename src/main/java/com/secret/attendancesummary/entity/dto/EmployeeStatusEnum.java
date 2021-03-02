package com.secret.attendancesummary.entity.dto;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.secret.attendancesummary.entity.EmployeeDelEnum;

/**
 * 员工状态
 *
 * @Description
 * @Author ljt
 * @Date 2020-12-10
 */
public enum EmployeeStatusEnum {
    ON_TRIAL(1, "试用", EmployeeDelEnum.ON_JOB),
    ON_JOB(2, "正式", EmployeeDelEnum.ON_JOB),
    APPLY_LEAVE(3, "申请离职", EmployeeDelEnum.LEAVE_JOB),
    AUTO_LEAVE(4, "自动离职", EmployeeDelEnum.LEAVE_JOB),
    ADVICE_LEAVE(5, "劝退", EmployeeDelEnum.LEAVE_JOB),
    EXPEL(6, "开除", EmployeeDelEnum.LEAVE_JOB);
    @EnumValue
    private final int val;
    private final String name;
    private final EmployeeDelEnum del;

    EmployeeStatusEnum(int val, String name, EmployeeDelEnum del) {
        this.val = val;
        this.name = name;
        this.del = del;
    }

    public Integer getValue() {
        return this.val;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }

    public EmployeeDelEnum getDel() {
        return this.del;
    }

    public static String getNameByVal(Integer val) {
        for (EmployeeStatusEnum value : EmployeeStatusEnum.values()) {
            if (value.getValue().equals(val)) {
                return value.getName();
            }
        }
        return null;
    }
}
