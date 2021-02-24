package com.secret.attendancesummary.entity;

import lombok.Data;

/**
 * 考勤申请媒体列表
 * @Author xieniuqi
 * @Date 2020-11-13 8:53
 * @Version 1.0
 **/
@Data
public class AttendanceApplyMediaListVo {
    /**
      * 图片完整路径
      */
	private String  url;
	/**
	 * 图片id
	 */
	private Integer id;


}
