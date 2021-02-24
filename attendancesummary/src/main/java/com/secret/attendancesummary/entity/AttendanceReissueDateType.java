package com.secret.attendancesummary.entity;
/**
     * 补卡日期类型,
     */
    //("补卡日期类型")
    public enum AttendanceReissueDateType {
        //Property("0,补卡日期类型错误")
        ReissueDateError("补卡日期类型错误",0,"补卡日期类型错误"),
        //Property("1,上午上班")
        MorningOnWork("上午上班",1,"上午上班"),
        //Property("2,上午下班")
        MorningOffWork("上午下班",2,"上午下班"),
        //Property("3,下午上班")
        AfternoonOnWork("下午上班",3,"下午上班"),
        //Property("4,下午下班")
        AfternoonOffWork("下午下班",4,"下午下班");

        AttendanceReissueDateType(String value, Integer code, String description){
            this.value = value;
            this.code = code;
            this.description=description;
        }

        private String value;

        private Integer code;
        private String description;

        public String getDescription() {
            return description;
        }

        public static AttendanceReissueDateType valueOf(Integer value){
            switch (value){
                case 1:
                    return AttendanceReissueDateType.MorningOnWork;
                case 2:
                    return AttendanceReissueDateType.MorningOffWork;
                case 3:
                    return AttendanceReissueDateType.AfternoonOnWork;
                case 4:
                    return AttendanceReissueDateType.AfternoonOffWork;
                default:
                    return AttendanceReissueDateType.ReissueDateError;
            }
        }
        public String getValue() {
            return value;
        }

        public Integer getCode() {
            return code;
        }

    }
