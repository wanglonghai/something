package com.secret.attendancesummary.Service.impl;

import com.alibaba.fastjson.JSONObject;
import com.secret.attendancesummary.Service.AttendanceCheckService;
import com.secret.attendancesummary.common.html.HttpMethod;
import com.secret.attendancesummary.common.html.HttpParamers;
import com.secret.attendancesummary.common.html.HttpUtils;
import com.secret.attendancesummary.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AttendanceCheckService
 * @Author Gavin
 * @Date 2021/2/5 14:12
 * @Description 描述
 * @Version 1.0
 */
@Service
@Slf4j
public class AttendanceCheckServiceImpl implements AttendanceCheckService {
    @Value("${attendance.serviceUrl}")
    public String serviceUrl;
    @Override
    public void loginAll(List<UserInfo> userInfos){
        userInfos.forEach(userInfo -> {
            login(userInfo);
        });
    }
    @Override
    public void login(UserInfo userInfo){
        if(userInfo==null
                || StringUtils.isBlank(userInfo.getName())
                ||StringUtils.isBlank(userInfo.getPwd())
                ||StringUtils.isBlank(serviceUrl)){
            log.error("!!!!!!!!!!!!!!!!!!!!config error!!!!!!!!!!!!!!!!!!!!");
            userInfo.setMessage("config error");
        }
        HttpParamers httpParamers=new HttpParamers(HttpMethod.POST);
        httpParamers.addParam("userName",userInfo.getName());
        httpParamers.addParam("passWord",userInfo.getPwd());
        httpParamers.addParam("sessionId","dd");
        httpParamers.addParam("clientId","1");
        httpParamers.setJsonParamer();
        Map<String, Object> result= HttpUtils.doRequest(serviceUrl+"/loginManager/pcLogin", httpParamers);
        if(result.get("code")!=null&&"200".equalsIgnoreCase(result.get("code").toString())){
            JSONObject jsonObject=(JSONObject)result.get("data");
            userInfo.setAccountId(Long.valueOf(jsonObject.get("accountId").toString()));
            log.info(jsonObject.get("userName").toString()+" login success...");
            jsonObject=(JSONObject)jsonObject.get("token");
            String token=jsonObject.getString("token");
            userInfo.setTk(token);
            log.info(token);
        }else{
            userInfo.setMessage("login fail,"+errorInfo(result));
            log.info("*****************login fail*****************");
        }
    }
    private String errorInfo(Map<String, Object> result) {
        String code=result.get("code")==null?"":result.get("code").toString();
        String message=result.get("message")==null?"":result.get("message").toString();
        return "code:"+code+",message"+message;
    }
}
