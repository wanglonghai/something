package com.secret.attendancesummary.Service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secret.attendancesummary.Service.AttendanceApplyService;
import com.secret.attendancesummary.Service.AttendanceRecordService;
import com.secret.attendancesummary.Service.AttendanceSummaryService;
import com.secret.attendancesummary.common.CommonUtils;
import com.secret.attendancesummary.common.PageUtils;
import com.secret.attendancesummary.common.html.HttpMethod;
import com.secret.attendancesummary.common.html.HttpParamers;
import com.secret.attendancesummary.common.html.HttpUtils;
import com.secret.attendancesummary.common.login.TokenUtils;
import com.secret.attendancesummary.dao.AttendanceApplyDao;
import com.secret.attendancesummary.dao.AttendanceSummaryDao;
import com.secret.attendancesummary.entity.*;
import com.secret.attendancesummary.entity.MySummaryAppletQueryResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AttendanceSummaryServiceImpl extends ServiceImpl<AttendanceSummaryDao, AttendanceSummary> implements AttendanceSummaryService {
    @Value("${attendance.serviceUrl}")
    public String serviceUrl;
    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    AttendanceSummaryService attendanceSummaryService;
    @Override
    public void SaveMulti(List<MySummaryAppletQueryResult> summaryAppletQueryResultList) {
        List<AttendanceSummary>  summaries=new ArrayList<>();
        summaryAppletQueryResultList.forEach(s->{
            summaries.add(new AttendanceSummary(s));
        });
        saveBatch(summaries);
    }
    @Override
    public List<MySummaryAppletQueryResult> getSummaryList(MySummaryAppletQueryDto mySummaryAppletQueryDto){
        List<MySummaryAppletQueryResult> result=new ArrayList<>();
        List<String> tokens=tokenUtils.getTokenForSummary();
        tokens.forEach(t->{
            result.addAll(getSummaryList(mySummaryAppletQueryDto,t));
        });
        return result;
    }

    private List<MySummaryAppletQueryResult> getSummaryList(MySummaryAppletQueryDto mySummaryAppletQueryDto,String token){
        HttpParamers httpParamers=new HttpParamers(HttpMethod.POST);
        httpParamers.addHeader("tk",token);
        if(mySummaryAppletQueryDto.getSummaryDates()!=null&&mySummaryAppletQueryDto.getSummaryDates().size()>0){
            httpParamers.addParam("summaryDates",mySummaryAppletQueryDto.getSummaryDates());
        }
        //0我的管理，1我的关注
        if(mySummaryAppletQueryDto.getPersonType()!=null){
            httpParamers.addParam("personType",mySummaryAppletQueryDto.getPersonType());
        }
        httpParamers.addParam("page",mySummaryAppletQueryDto.getPage().toString());
        httpParamers.addParam("limit",mySummaryAppletQueryDto.getLimit().toString());
        httpParamers.setJsonParamer();
        String message="";
        Map<String, Object> result= HttpUtils.doRequest(serviceUrl+"/applet/attendance/attendanceWorkSummary/listAppletMyRelateSummary", httpParamers);
        if(result.get("code")!=null&&"200".equalsIgnoreCase(result.get("code").toString())){
            JSONObject jsonObject=(JSONObject)result.get("data");
            Integer totalCount=(Integer)jsonObject.get("totalCount");
            if(totalCount>0){
                List<MySummaryAppletQueryResult> jsonArray= JSON.parseObject(JSON.toJSONString(jsonObject.get("list")),new TypeReference<List<MySummaryAppletQueryResult>>(){});
                return jsonArray;
            }else{
                message="查询结果为空";
                log.info(message);
            }

        }else{
            message=result.get("message")==null?"未知错误":result.get("message").toString();
            log.info(message);
        }
        return new ArrayList<>();
    }


    private List<MySummaryAppletQueryResult> readAll(MySummaryAppletQueryDto MySummaryAppletQueryDto){
        List<MySummaryAppletQueryResult > all=new ArrayList<>();
        Integer currPage=1;
        MySummaryAppletQueryDto.setPage(currPage);
        MySummaryAppletQueryDto.setLimit(500);
        //读我的关注
        MySummaryAppletQueryDto.setPersonType(1);
        List<MySummaryAppletQueryResult> mySummaryAppletQueryResultList=getSummaryList(MySummaryAppletQueryDto);
        all.addAll(mySummaryAppletQueryResultList);
        MySummaryAppletQueryDto.setPersonType(0);
        //读我的管理
        mySummaryAppletQueryResultList=getSummaryList(MySummaryAppletQueryDto);
        all.addAll(mySummaryAppletQueryResultList);
        HashSet h = new HashSet(all);
        all.clear();
        all.addAll(h);
        return all;
    }
    @Override
    public List<MySummaryAppletQueryResult> makeDataFromHttp(MySummaryAppletQueryDto MySummaryAppletQueryDto) {
        List<MySummaryAppletQueryResult> MySummaryAppletQueryResultList=readAll(MySummaryAppletQueryDto);
        log.info("日志总数量："+MySummaryAppletQueryResultList.size());
        List<List<MySummaryAppletQueryResult>> d=CommonUtils.splitList(MySummaryAppletQueryResultList,500);
        log.info("批次："+d.size());
        d.forEach(a->dealData(a));
        return MySummaryAppletQueryResultList;
    }
    private  void dealData(List<MySummaryAppletQueryResult> MySummaryAppletQueryResultList){
        if(MySummaryAppletQueryResultList.size()>0){
            attendanceSummaryService.SaveMulti(MySummaryAppletQueryResultList);
        }
    }

}
