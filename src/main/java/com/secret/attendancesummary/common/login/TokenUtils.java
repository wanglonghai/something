package com.secret.attendancesummary.common.login;

import com.secret.attendancesummary.Service.AttendanceCheckService;
import com.secret.attendancesummary.entity.UserInfo;
import com.secret.attendancesummary.entity.UserList;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @ClassName Token
 * @Author Gavin
 * @Date 2021/2/23 11:51
 * @Description 描述
 * @Version 1.0
 */
@Data
@Component
public class TokenUtils {
    //60分钟
    public static final Integer expireTime=60*60*1000;
    @Autowired
    UserList userList;
    @Autowired
    AttendanceCheckService attendanceCheckService;
    @PostConstruct
    public void init(){
        userList.getList().forEach(s->{
                //启动时先不登录了，怕剔除别人的正常登录
                //attendanceCheckService.login(s);
        });
    }
    public String getToken(){
        return getToken(userList.getList().get(0).getName());
    }
    public List<String> getTokenForSummary(){
        List<String> tokens=new ArrayList<>();
        userList.getList().forEach(userInfo -> {
            if(Boolean.TRUE.equals(userInfo.getIsSummary())){
                tokens.add(getToken(userInfo.getName()));
            }
        });
        return tokens;
    }
    public String getToken(String userName){
        for(UserInfo s:userList.getList()){
            if(s.getName().equalsIgnoreCase(userName)){
                if(StringUtils.isBlank(s.getTk())||(new Date().getTime()-s.getTokenTime().getTime())>expireTime-100){
                    refreshToken(userName);
                }
                return s.getTk();
            }
        }
        return null;
    }

    private void refreshToken(String userName) {
        userList.getList().forEach(s->{
            if(s.getName().equalsIgnoreCase(userName)){
                attendanceCheckService.login(s);
            }
        });
    }
}
