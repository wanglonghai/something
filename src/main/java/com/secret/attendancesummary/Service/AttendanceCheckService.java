package com.secret.attendancesummary.Service;
import com.secret.attendancesummary.entity.UserInfo;

import java.util.List;

/**
 * @ClassName AttendanceCheckService
 * @Author Gavin
 * @Date 2021/2/5 14:12
 * @Description 描述
 * @Version 1.0
 */
public interface AttendanceCheckService {
    void login(UserInfo userInfo);
    void loginAll(List<UserInfo> userInfos);
}
