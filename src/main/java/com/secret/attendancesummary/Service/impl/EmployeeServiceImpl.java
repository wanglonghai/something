package com.secret.attendancesummary.Service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secret.attendancesummary.Service.EmployeeService;
import com.secret.attendancesummary.common.CommonUtils;
import com.secret.attendancesummary.common.DateUtil;
import com.secret.attendancesummary.common.EncryptUtil;
import com.secret.attendancesummary.common.PageUtils;
import com.secret.attendancesummary.common.html.HttpMethod;
import com.secret.attendancesummary.common.html.HttpParamers;
import com.secret.attendancesummary.common.html.HttpUtils;
import com.secret.attendancesummary.common.login.TokenUtils;
import com.secret.attendancesummary.dao.EmployeeDao;
import com.secret.attendancesummary.entity.AttendanceApplyListQuery;
import com.secret.attendancesummary.entity.EmployeeDetail;
import com.secret.attendancesummary.entity.EmployeeInfo;
import com.secret.attendancesummary.entity.MySummaryAppletQueryResult;
import com.secret.attendancesummary.entity.dto.AttendanceApplyListVo;
import com.secret.attendancesummary.entity.dto.AttendanceCheckBaseDetailsVo;
import com.secret.attendancesummary.entity.dto.EmployeeInfoDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
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
@Slf4j
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao,EmployeeInfo> implements EmployeeService {

    private static final String SECRET = "xjf@666Abc";
    @Value("${attendance.serviceUrl}")
    public String serviceUrl;
    @Autowired
    TokenUtils tokenUtils;
    @Override
    public void SaveMulti(List<EmployeeInfo> summaryAppletQueryResultList) {
        log.info("人员总数量："+summaryAppletQueryResultList.size());
        List<List<EmployeeInfo>> d= CommonUtils.splitList(summaryAppletQueryResultList,500);
        log.info("批次："+d.size());
        d.forEach(a->dealData(a));
    }

    private void dealData(List<EmployeeInfo> a) {
        saveBatch(a);
    }
    @Override
    public void doDataSchedule(Date date){
        EmployeeInfoDto employeeInfoDto=new EmployeeInfoDto();
        employeeInfoDto.setPage(1);
        employeeInfoDto.setLimit(2000);
        employeeInfoDto.setMinJoinDate(date);
        employeeInfoDto.setMaxJoinDate(date);
        List<EmployeeInfo> employeeInfos=readAll(employeeInfoDto);
        log.info("查询到员工个数："+employeeInfos.size());
        employeeInfos.forEach(s->{
            if(s.getAccountId()!=null){
                BeanUtils.copyProperties(getDetailFromRemote(s.getAccountId()),s);
                log.info("获取信息："+JSON.toJSONString(s));
            }
        });
        SaveMulti(employeeInfos);
    }
    @Override
    public List<String> getColumnValues(String fieldName) {
        return this.baseMapper.getColumnValues(fieldName);
    }

    private List<EmployeeInfo> readAll(EmployeeInfoDto employeeInfoDto){
        List<EmployeeInfo> all=new ArrayList<>();
        Integer currPage=1;
        employeeInfoDto.setPage(currPage);
        employeeInfoDto.setLimit(500);
        PageUtils<EmployeeInfo> attendanceApplyListVoList=getDataFromRemote(employeeInfoDto);
        all.addAll(attendanceApplyListVoList.getList());
        while (attendanceApplyListVoList.getTotalPage()>currPage){
            currPage++;
            employeeInfoDto.setPage(currPage);
            employeeInfoDto.setLimit(500);
            attendanceApplyListVoList=getDataFromRemote(employeeInfoDto);
            all.addAll(attendanceApplyListVoList.getList());
        }
        return all;
    }
    @Override
    public PageUtils<EmployeeInfo> getDataFromRemote(EmployeeInfoDto employeeInfoDto) {
        PageUtils<EmployeeInfo> pageUtils;
        HttpParamers httpParamers=new HttpParamers(HttpMethod.POST);
        httpParamers.addHeader("tk",tokenUtils.getToken());
        if(employeeInfoDto.getMinJoinDate()!=null){
            httpParamers.addParam("minJoinDate",DateUtil.getFormatTimeString(employeeInfoDto.getMinJoinDate(),"yyyy-MM-dd")+" 00:00:01");
        }
        if(employeeInfoDto.getMaxJoinDate()!=null){
            httpParamers.addParam("maxJoinDate", DateUtil.getFormatTimeString(employeeInfoDto.getMaxJoinDate(),"yyyy-MM-dd")+" 23:59:59");
        }
        httpParamers.addParam("page",employeeInfoDto.getPage().toString());
        httpParamers.addParam("limit",employeeInfoDto.getLimit().toString());
        httpParamers.setJsonParamer();
        String message="";
        Map<String, Object> result= HttpUtils.doRequest(serviceUrl+"/employee/employeeList", httpParamers);
        if(result.get("code")!=null&&"200".equalsIgnoreCase(result.get("code").toString())){
            JSONObject jsonObject=(JSONObject)result.get("data");
            Integer totalCount=(Integer)jsonObject.get("totalCount");
            if(totalCount>0){
                List<EmployeeInfo> jsonArray= JSON.parseObject(JSON.toJSONString(jsonObject.get("list")),new TypeReference<List<EmployeeInfo>>(){});
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
        return  new PageUtils<>();
    }
    @SneakyThrows
    private EmployeeDetail getDetailFromRemote(Integer accountId){
        HttpParamers httpParamers=new HttpParamers(HttpMethod.POST);
        httpParamers.addHeader("tk",tokenUtils.getToken());
        httpParamers.addParam("id",accountId);
        Long timestamp=System.currentTimeMillis();
        String paramsString=accountId.toString();
        String sign=EncryptUtil.md5Encode(SECRET + URLEncoder.encode(paramsString,"UTF-8")
                .replace("+","%20")
                .replace("%28","(")
                .replace("%29",")")
                .replace("%27","'")
                .replace("%21","!")
                .replace("%7E","~")
                + timestamp).toLowerCase();
        httpParamers.addHeader("sign",sign);
        httpParamers.addHeader("timestamp",timestamp.toString());
        String message="";
        Map<String, Object> result= HttpUtils.doRequest(serviceUrl+"/employee/data", httpParamers);
        if(result.get("code")!=null&&"200".equalsIgnoreCase(result.get("code").toString())){
            if(result.get("data")!=null){
                EmployeeDetail data= JSON.parseObject(JSON.toJSONString(result.get("data")),EmployeeDetail.class);
                return data;
            }else{
                message="查询结果为空";
                log.info(message);
            }

        }else{
            message=result.get("message")==null?"未知错误":result.get("message").toString();
            log.info(message);
        }
        return new EmployeeDetail();
    }
}
