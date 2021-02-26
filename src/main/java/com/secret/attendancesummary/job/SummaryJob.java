package com.secret.attendancesummary.job;
import com.alibaba.fastjson.JSON;
import com.secret.attendancesummary.Service.AttendanceRecordService;
import com.secret.attendancesummary.Service.AttendanceSummaryService;
import com.secret.attendancesummary.entity.AttendanceApplyListQuery;
import com.secret.attendancesummary.entity.AttendanceApplyListVo;
import com.secret.attendancesummary.entity.MySummaryAppletQueryDto;
import com.secret.attendancesummary.entity.MySummaryAppletQueryResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class SummaryJob {
    @Autowired
    AttendanceSummaryService attendanceSummaryService;
    /**
     *
     */
    @Scheduled(cron = "59 59 4 * * ?")
    @SneakyThrows
    public void doSummaryData() {
       doSummaryData(-1);
    }
    public void doSummaryData(Integer day){
        log.info("任务开启，开始收集日志data");
        //抽取的数据
        MySummaryAppletQueryDto attendanceApplyListQuery=new MySummaryAppletQueryDto();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, day);//昨天
        Date yesterday=calendar.getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> d=new ArrayList<>();
        d.add(simpleDateFormat.format(yesterday).split(" ")[0]);
//        d.add("2021-02-22");
//        d.add("2021-02-21");
//        d.add("2021-02-20");
//        d.add("2021-02-19");
        attendanceApplyListQuery.setSummaryDates(d);
        attendanceApplyListQuery.setPage(1);
        attendanceApplyListQuery.setLimit(500);
        List<MySummaryAppletQueryResult> data=attendanceSummaryService.makeDataFromHttp(attendanceApplyListQuery);
        log.info("成功收集日志data："+data.size()+"=="+JSON.toJSONString(data));
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
