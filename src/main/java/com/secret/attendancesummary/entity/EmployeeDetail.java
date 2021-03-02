package com.secret.attendancesummary.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.secret.attendancesummary.entity.dto.PartnerLevelEnum;
import com.secret.attendancesummary.entity.dto.PartnerTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName EmployeeDetail
 * @Author Gavin
 * @Date 2021/3/2 11:55
 * @Description 描述
 * @Version 1.0
 */
@Data
public class EmployeeDetail {
    /**
     * 姓名
     */
    private String perName;
    /**
     * 身份证id
     */
    private String cardId;
    /**
     * 性别：0男，1女
     */
    private Integer sex;

    /**
     * 现居住地址
     */
    private String address;
    /**
     * 学历ID
     * @see EducationEnum
     */
    private Integer education;

    private String educationDesc;

    public String getEducationDesc() {
        this.educationDesc=EducationEnum.getNameByVal(this.education);
        return educationDesc;
    }

    /**
     * 专业
     */
    private String speciality;

    /**
     * Email
     */
    private String email;
    /**
     * QQ
     */
    private String qq;

    /**
     * 电话号码
     */
    private String tel;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    /**
     * 紧急人联系人
     */
    private String emergencyContact;
    /**
     * 关系
     */
    private String relationship;
    /**
     * 紧急联系人电话号码
     */
    private String contactTelephone;
    /**
     * 毕业学校
     */
    private String graduateSchool;
    /**
     * 户口所在地邮编
     */
    private String residenceCode;
    /**
     * 户口所在地
     */
    private String accountAddress;
    /**
     * 银行卡号
     */
    private String bankcard;

    /**
     * 毕业时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date graduation;
    /**
     * 头像
     */
    private String userImage;
    /**
     * 户籍地址
     */
    private String nativePlace;

    /**
     * 银行
     */
    private String bank;

    /**
     * 合伙人级别
     * @see PartnerLevelEnum
     */
    private String partnerLevel;

    /**
     * 合伙人类型
     * @see PartnerTypeEnum
     */
    private String partnerType;
    /**
     * 微信号
     */
    private String weChat;

}
