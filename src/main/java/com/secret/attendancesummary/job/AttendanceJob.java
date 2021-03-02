package com.secret.attendancesummary.job;
import com.alibaba.fastjson.JSON;
import com.secret.attendancesummary.Service.AttendanceRecordService;
import com.secret.attendancesummary.entity.AttendanceApplyListQuery;
import com.secret.attendancesummary.entity.dto.AttendanceApplyListVo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AttendanceJob
 * @Author Gavin
 * @Date 2021/2/23 10:05
 * @Description 描述
 * @Version 1.0
 */
@Component
@Slf4j
public class AttendanceJob {
    @Autowired
    AttendanceRecordService attendanceRecordService;
    /**
     *
     */
    @Scheduled(cron = "59 45 4 * * ?")
    public void doApplyData() {
        doApplyData(-1);
    }
    @SneakyThrows
    public void doApplyData(Integer d){
        log.info("任务开启，开始收集审批data");
        //抽取的数据
        AttendanceApplyListQuery attendanceApplyListQuery=new AttendanceApplyListQuery();
       if(!Integer.valueOf(88888888).equals(d)){
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(new Date());
           calendar.add(Calendar.DAY_OF_MONTH, d);//昨天
           Date yesterday=calendar.getTime();
           SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           Date startDate=simpleDateFormat.parse(simpleDateFormat.format(yesterday).split(" ")[0]+" 00:00:00");
           Date endDate=simpleDateFormat.parse(simpleDateFormat.format(yesterday).split(" ")[0]+" 23:59:00");
           attendanceApplyListQuery.setApplyStartTime(startDate);
           attendanceApplyListQuery.setApplyEndTime(endDate);
       }
        attendanceApplyListQuery.setPage(1);
        attendanceApplyListQuery.setLimit(500);
        List<AttendanceApplyListVo> data=attendanceRecordService.makeDataFromHttp(attendanceApplyListQuery);
        log.info("成功收集审批data："+data.size()+"=="+JSON.toJSONString(data));
    }
    @SneakyThrows
    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.DAY_OF_MONTH, -1);//昨天
//        Date yestoday=calendar.getTime();
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date startDate=simpleDateFormat.parse(simpleDateFormat.format(yestoday).split(" ")[0]+" 00:00:00");
//        Date endDate=simpleDateFormat.parse(simpleDateFormat.format(yestoday).split(" ")[0]+" 23:59:00");
//        System.out.println(simpleDateFormat.format(startDate));
//        System.out.println(simpleDateFormat.format(endDate));
    }
}
