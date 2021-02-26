package com.secret.attendancesummary.entity;

public enum AttendanceEnum {
    ATTENDANCE_NONE(-1,""),
    //AttendanceRuleDay 考勤规则日类型
    ATTENDANCE_RULE_DAY_TYPE_DEPT(1,"部门"),
    ATTENDANCE_RULE_DAY_TYPE_PERSON(2,"人员"),

    //AttendanceRecord（签到结果） 0正常考勤、1迟到、2早退、3旷工、4请假、5、迟到早退
    ATTENDANCE_CHECK_IN_RESULT_NORMAL(0,"正常"),
    ATTENDANCE_CHECK_IN_RESULT_LATE(1,"迟到"),
    ATTENDANCE_CHECK_IN_RESULT_EARLY(2,"早退"),
    ATTENDANCE_CHECK_IN_RESULT_ABSENTEEISM(3,"旷工"),
    ATTENDANCE_CHECK_IN_RESULT_LEAVE(4,"请假"),
    //ATTENDANCE_CHECK_IN_RESULT_LATE_AND_EARLY(5,"迟到早退"),


    //AttendanceRecord do_checking字段 是否考勤（1:需要考勤,3免考勤）
    ATTENDANCE_DO_CHECK_IN_NEED_ATTENDANCE(1,"需要考勤"),
    ATTENDANCE_DO_CHECK_IN_IS_FREEDOM(3,"免考勤"),

    //考勤规则日类型
    ATTENDANCE_FREE(1,"免考勤"),
    ATTENDANCE_FREE_NO(0,"正常考勤"),
    ATTENDANCE_FREE_NOTDAY(2,"非考勤日"),

    //考勤请假申请 0上午、1下午
    ATTENDANCE_APPLY_HOLIDAY_MORNING(0,"上午"),
    ATTENDANCE_APPLY_HOLIDAY_AFTERNOON(1,"下午"),

    //AttendanceAuditRecord 人员类型：1：审核人员、2：抄送人员
    ATTENDANCE_AUDIT_RECORD_TYPE_AUDITOR(1,"审核人员"),
    ATTENDANCE_AUDIT_RECORD_TYPE_COPY_PERSONAL(2,"抄送人员"),

    //AttendanceApply 申请类型：1请假、2补卡
    ATTENDANCE_APPLY_HOLIDAY(1,"请假"),
    ATTENDANCE_APPLY_REISSUE(2,"补卡"),

    //AttendanceAuditRecord AttendanceApply AttendanceFreedomApply 状态：0：未审核、1：审核通过、2：不通过
    ATTENDANCE_AUDIT_RECORD_STATUS_UN_REVIEWED(0,"未审核"),
    ATTENDANCE_AUDIT_RECORD_STATUS_PASS(1,"通过通过"),
    ATTENDANCE_AUDIT_RECORD_STATUS_NO_PASS(2,"不通过"),

    //AttendanceRuleDay doChecking 是否考勤：0不考勤，1考勤，2请假
    ATTENDANCE_RULE_DAY_DO_CHECKING_FREEDOM(0,"不考勤"),
    ATTENDANCE_RULE_DAY_DO_CHECKING_FREEDOM_NO(1,"考勤"),
    //ATTENDANCE_RULE_DAY_DO_CHECKING_IS_FREEDOM(3,"免考勤"),
    //ATTENDANCE_RULE_DAY_DO_CHECKING_HOLIDAY(2,"请假"),

    ;

    public String name;

    public Integer value;

    AttendanceEnum(Integer value, String name){
        this.name = name;
        this.value = value;
    }

    public static AttendanceEnum getCheckInResultEnum(Integer code){
        if(code==null){
            return ATTENDANCE_NONE;
        }
        switch (code){
            case 0:return ATTENDANCE_CHECK_IN_RESULT_NORMAL;
            case 1:return ATTENDANCE_CHECK_IN_RESULT_LATE;
            case 2:return ATTENDANCE_CHECK_IN_RESULT_EARLY;
            case 3:return ATTENDANCE_CHECK_IN_RESULT_ABSENTEEISM;
            case 4:return ATTENDANCE_CHECK_IN_RESULT_LEAVE;
        }
        return ATTENDANCE_NONE;
    }
    public static AttendanceEnum getFreeTypeEnum(Integer code){
        if(code==null){
            return ATTENDANCE_NONE;
        }
        switch (code){
            case 1:return ATTENDANCE_FREE;
            case 0:return ATTENDANCE_FREE_NO;
            case 2:return ATTENDANCE_FREE_NOTDAY;
        }
        return ATTENDANCE_NONE;
    }
}
