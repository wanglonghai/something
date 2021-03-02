package com.secret.attendancesummary.Service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.secret.attendancesummary.Service.AttendanceApplyService;
import com.secret.attendancesummary.Service.AttendanceRecordService;
import com.secret.attendancesummary.common.CommonUtils;
import com.secret.attendancesummary.common.PageUtils;
import com.secret.attendancesummary.common.html.HttpMethod;
import com.secret.attendancesummary.common.html.HttpParamers;
import com.secret.attendancesummary.common.html.HttpUtils;
import com.secret.attendancesummary.common.login.TokenUtils;
import com.secret.attendancesummary.entity.AttendanceApplyListQuery;
import com.secret.attendancesummary.entity.dto.AttendanceApplyListVo;
import com.secret.attendancesummary.entity.dto.AttendanceCheckBaseDetailsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
@Slf4j
public class AttendanceRecordServiceImpl implements AttendanceRecordService {
    @Value("${attendance.serviceUrl}")
    public String serviceUrl;
    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    AttendanceApplyService attendanceApplyService;
    @Override
    public PageUtils<AttendanceApplyListVo> getAttendanceApplyList(AttendanceApplyListQuery attendanceApplyListQuery){
        PageUtils<AttendanceApplyListVo> pageUtils;
        HttpParamers httpParamers=new HttpParamers(HttpMethod.POST);
        httpParamers.addHeader("tk",tokenUtils.getToken());
        httpParamers.addParam("page",attendanceApplyListQuery.getPage().toString());
        httpParamers.addParam("limit",attendanceApplyListQuery.getLimit().toString());
        if(attendanceApplyListQuery.getApplyStartTime()!=null){
            httpParamers.addParam("applyStartTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(attendanceApplyListQuery.getApplyStartTime()));
        }
        if(attendanceApplyListQuery.getApplyEndTime()!=null){
            httpParamers.addParam("applyEndTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(attendanceApplyListQuery.getApplyEndTime()));
        }
        httpParamers.setJsonParamer();
        String message="";
        Map<String, Object> result= HttpUtils.doRequest(serviceUrl+"/attendance/apply/list", httpParamers);
        if(result.get("code")!=null&&"200".equalsIgnoreCase(result.get("code").toString())){
            JSONObject jsonObject=(JSONObject)result.get("data");
            Integer totalCount=(Integer)jsonObject.get("totalCount");
            if(totalCount>0){
                List<AttendanceApplyListVo> jsonArray= JSON.parseObject(JSON.toJSONString(jsonObject.get("list")),new TypeReference<List<AttendanceApplyListVo>>(){});
                pageUtils=new PageUtils<>(jsonArray,totalCount,(Integer) jsonObject.get("pageSize"),(Integer) jsonObject.get("currPage"));
                return pageUtils;
            }else{
                message="查询结果为空";
                log.info(message);
            }

        }else{
            message=result.get("message")==null?"未知错误":result.get("message").toString();
            log.info(message);
        }
        return new PageUtils<>();
    }

    @Override
    public AttendanceCheckBaseDetailsVo getApplyDetails(Integer applyid) {
        HttpParamers httpParamers=new HttpParamers(HttpMethod.GET);
        httpParamers.addHeader("tk",tokenUtils.getToken());
        String message="";
        Map<String, Object> result= HttpUtils.doRequest(serviceUrl+"/attendance/apply/base/details/"+applyid, httpParamers);
        if(result.get("code")!=null&&"200".equalsIgnoreCase(result.get("code").toString())){
            if(result.get("data")!=null){
                AttendanceCheckBaseDetailsVo jsonArray= JSON.parseObject(JSON.toJSONString(result.get("data")),AttendanceCheckBaseDetailsVo.class);
                return jsonArray;
            }else{
                message="查询结果为空";
                log.info(message);
            }

        }else{
            message=result.get("message")==null?"未知错误":result.get("message").toString();
            log.info(message);
        }
        return null;
    }

    private List<AttendanceApplyListVo > readAll(AttendanceApplyListQuery attendanceApplyListQuery){
        List<AttendanceApplyListVo > all=new ArrayList<>();
        Integer currPage=1;
        attendanceApplyListQuery.setPage(currPage);
        attendanceApplyListQuery.setLimit(500);
        PageUtils<AttendanceApplyListVo> attendanceApplyListVoList=getAttendanceApplyList(attendanceApplyListQuery);
        all.addAll(attendanceApplyListVoList.getList());
        while (attendanceApplyListVoList.getTotalPage()>currPage){
            currPage++;
            attendanceApplyListQuery.setPage(currPage);
            attendanceApplyListQuery.setLimit(500);
            attendanceApplyListVoList=getAttendanceApplyList(attendanceApplyListQuery);
            all.addAll(attendanceApplyListVoList.getList());
        }
        return all;
    }
    @Override
    public List<AttendanceApplyListVo> makeDataFromHttp(AttendanceApplyListQuery attendanceApplyListQuery) {
        List<AttendanceApplyListVo> attendanceApplyListVoList=readAll(attendanceApplyListQuery);
        log.info("申请总数量："+attendanceApplyListVoList.size());
        List<List<AttendanceApplyListVo>> d=CommonUtils.splitList(attendanceApplyListVoList,500);
        log.info("批次："+d.size());
        d.forEach(a->dealData(a));
        return attendanceApplyListVoList;
    }
    private  void dealData(List<AttendanceApplyListVo> attendanceApplyListVoList){
        if(attendanceApplyListVoList.size()>0){
            AttendanceCheckBaseDetailsVo attendanceCheckBaseDetailsVo;
            for(AttendanceApplyListVo a : attendanceApplyListVoList){
                log.info("申请id："+a.getId());
                attendanceCheckBaseDetailsVo=getApplyDetails(a.getId());
                if(attendanceCheckBaseDetailsVo!=null){
                    log.info("申请原因："+attendanceCheckBaseDetailsVo.getApplyReason());
                    a.setApplyReason(attendanceCheckBaseDetailsVo.getApplyReason());
                    a.setApplyStartTimeStr(attendanceCheckBaseDetailsVo.getApplyStartTimeStr());
                    a.setApplyEndTimeStr(attendanceCheckBaseDetailsVo.getApplyEndTimeStr());
                    attendanceCheckBaseDetailsVo.getApplyMediaList().forEach(media->{
                        a.setApplyMedia(a.getApplyMedia()+";"+media.getUrl());
                    });
                }
            }
            attendanceApplyService.SaveMulti(attendanceApplyListVoList);
        }
    }

}
