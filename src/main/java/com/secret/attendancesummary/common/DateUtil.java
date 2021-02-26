package com.secret.attendancesummary.common;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author ljt
 * @create 2020/2/11 15:28
 */
public class DateUtil {

    /**
     * 英文简写（默认）如：12-01
     */
    public static String FORMAT_MONTH_DAY = "MM-dd";
    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_MONTH = "yyyy-MM";
    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";
    /**
     * 英文简写（默认）如：23:15:06
     */
    public  static  String  FORMAT_HOUR_MINUTE_SECOND="HH:mm:ss";

    /**
     * 英文到分钟  如：2010-12-01 23:15:06
     */

    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    /**
     * 英文全称  如：2010-12-01 23:15:06
     */

    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */

    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 中文简写  如：2010年12月01日
     */

    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */

    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间
     */

    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    /**
     * 天数转月份的参数
     */
    public static  Integer  DAY_TO_MONTH=30;

    /**
     * 时间格式化时间
     * @param time
     * @param format
     * @return
     */
    public static Date getFormatTime(Date time, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return getFormatTime(df.format(time),format);
    }

    /**
     * 时间格式化
     */

    public static String getFormatTimeString(Date time, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(time);
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date getFormatTime(String str, String format) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当前时间
     */

    public static String getTimeString() {

        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);

        Calendar calendar = Calendar.getInstance();

        return df.format(calendar.getTime());

    }

    /**
     * 获取当前月份YYYY-MM
     */

    public static String geMonthString(Date date) {

        SimpleDateFormat df = new SimpleDateFormat(FORMAT_MONTH);

        return df.format(date.getTime());

    }
    /**
     * 获取当前月份和日期MM-dd
     */
    public  static  String getMonthAndDayString(Date date){
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_MONTH_DAY);
        return df.format(date.getTime());

    }

    /**
     * 获取YYYY-MM-DD
     */
    public static String getDayString(Date date) {
        if(date==null){
            return  null;
        }
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_SHORT);

        return df.format(date.getTime());

    }

    /**
     * 获取时分秒HH:mm:ss
     */
    public static String getHouseMinuteSecond(Date date) {
        if(date==null){
            return  null;
        }
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_HOUR_MINUTE_SECOND);

        return df.format(date.getTime());

    }

    public static String getDateString() {

        SimpleDateFormat df = new SimpleDateFormat(FORMAT_SHORT);

        Calendar calendar = Calendar.getInstance();

        return df.format(calendar.getTime());

    }

    /**
     * 获取当前时间
     * 1则代表的是对年份操作，2是对月份操作，3是对星期操作，5是对日期操作，11是对小时操作，12是对分钟操作，13是对秒操作，14是对毫秒操作。
     */

    public static String getTimeStringAdd(int code, Integer time) {

        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);

        Calendar calendar = Calendar.getInstance();

        calendar.add(code, time);

        return df.format(calendar.getTime());
    }


    /**
     * 获取日期年份
     *
     * @param date 日期
     * @return
     */

    public static String getYear(Date date) {

        return getTimeString().substring(0, 4);

    }

    /**
     * 功能描述：返回年
     *
     * @param date Date 日期
     * @return 返回月份
     */

    public static int getYearByDate(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        return calendar.get(Calendar.YEAR);

    }

    /**
     * 功能描述：返回月
     *
     * @param date Date 日期
     * @return 返回月份
     */

    public static int getMonth(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        return calendar.get(Calendar.MONTH) + 1;

    }


    /**
     * 返回前N个月，格式为 yyyy-mm-dd
     * @param number 前N月
     * @return
     */
    public static String getLastMonth(Integer number){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,-number);

        Integer mouth=calendar.get(Calendar.MONTH)+1;
        Integer year=calendar.get(Calendar.YEAR);
        if(mouth<10){
            return year+"-0"+mouth+"-01";
        }else{
            return year+"-"+mouth+"-01";
        }
    }


    /**
     * 功能描述：返回日期
     *
     * @param date Date 日期
     * @return 返回日份
     */

    public static int getDay(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_MONTH);

    }


    /**
     * 功能描述：返回小时
     *
     * @param date 日期
     * @return 返回小时
     */

    public static int getHour(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        return calendar.get(Calendar.HOUR_OF_DAY);

    }


    /**
     * 功能描述：返回分
     *
     * @param date 日期
     * @return 返回分钟
     */

    public static int getMinute(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        return calendar.get(Calendar.MINUTE);

    }


    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */

    public static int getSecond(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        return calendar.get(Calendar.SECOND);

    }


    /**
     * 功能描述：返回毫
     *
     * @param date 日期
     * @return 返回毫
     */

    public static long getMillis(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        return calendar.getTimeInMillis();

    }

    /**
     * 是否大于现在的时间
     * true 大于
     *
     * @param date
     * @param dateFormate
     * @return
     */
    public static boolean isGtNow(Date date, String dateFormate) {
        boolean flag = false;
        try {
            Date nowdt = new Date();
            Date compt = date;
            long nowtm = nowdt.getTime();
            long comptm = compt.getTime();
            if (comptm > nowtm) {
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }

        return flag;
    }

    /**
     * 判断字符串是否正确的日期格式
     * @param str
     * @return
     */
    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (Exception e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    /**
     * 日期转时间戳
     *
     * @param dateTime
     * @return
     */
    public static Long toTimeStamp(Date dateTime) {
        return dateTime.getTime();
    }

    public static Long toTimeStamp(java.time.LocalDateTime dateTime) {
        return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            return day2 - day1;
        }
    }
    /**
     * date2比date1多的月树
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentMonth(Date date1, Date date2) {
        int result=0;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int year1=cal1.get(Calendar.YEAR);
        int year2=cal2.get(Calendar.YEAR);

        int month1=cal1.get(Calendar.MONTH)+1;
        int month2=cal2.get(Calendar.MONTH)+1;


        result=(year2-year1)*12+month2-month1;

        return  Math.abs(result);
    }
    /**
     * 获取当前时间
     * code 1则代表的是对年份操作，2是对月份操作，3是对星期操作，5是对日期操作，11是对小时操作，12是对分钟操作，13是对秒操作，14是对毫秒操作。
     * time 操作数值
     */
    public static Date getTimeDateAdd(int code, Integer time) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(code, time);
        return calendar.getTime();
    }

    /**
     * code 1则代表的是对年份操作，2是对月份操作，3是对星期操作，5是对日期操作，11是对小时操作，12是对分钟操作，13是对秒操作，14是对毫秒操作。
     * time 操作数值
     */
    public static Date getTimeDateAdd(int code, Integer addTime,Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(code, addTime);
        return calendar.getTime();
    }

    /**
     * 获取当前时间
     * code 1则代表的是对年份操作，2是对月份操作，3是对星期操作，5是对日期操作，11是对小时操作，12是对分钟操作，13是对秒操作，14是对毫秒操作。
     * time 操作数值
     */
    public static Date getDateAddHouse(Date date,Integer time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, time);
        return calendar.getTime();
    }

    /**
     * 日期减去指定天数
     *
     * @param date
     * @param number
     * @return
     */
    public static Date betweenNum(Date date, Integer number) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -number);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当前月第一天
     *
     * @return
     */
    public static String getMonthFirstDay(Date date1) {
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.setTime(date1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String firstDay = getFormatTimeString(cal_1.getTime(), FORMAT_SHORT);
        return firstDay;
    }

    /**
     * 获取当前月最后一天
     *
     * @return
     */
    public static Date getMonthLastDayByDate(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return ca.getTime();
    }

    /**
     * 获取当前月第一天
     *
     * @return
     */
    public static Date getMonthFirstDayByDate(Date date) {
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.setTime(date);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return cal_1.getTime();
    }



    /**
     * 获取明天的零点零分 毫秒数
     * @param currentDate
     * @return
     */
    public static Long getRemainSecondsOneDay(Date currentDate) {
        Calendar midnight = Calendar.getInstance();
        midnight.setTime(currentDate);
        midnight.add(Calendar.DAY_OF_MONTH, 1);
        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        midnight.set(Calendar.SECOND, 0);
        midnight.set(Calendar.MILLISECOND, 0);
        Long seconds = (midnight.getTime().getTime() - currentDate.getTime()) / 1000;
        return seconds;
    }

    /**
     * 是否在时间范围内
     * @param timestamp
     * @param i         毫秒
     * @return
     */
    public static boolean isInTime(String timestamp, int i) {
        if (timestamp == null) {
            return false;
        }

        long times = Long.parseLong(timestamp);

        long start = System.currentTimeMillis() - i;
        long end = System.currentTimeMillis() + i;
        if (times >= start && end <= end) {
            return true;
        }
        return false;
    }


    /**
     * 判断字符串是否正确的日期格式-分隔符为-
     * @param str
     * @return
     */
    public static boolean isValidDate2(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年-两位月份-两位日期，注意yyyy-MM-dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007-02-29会被接受，并转换成2007-03-01
            format.setLenient(false);
            format.parse(str);
        } catch (Exception e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(Date dt) {
        int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }
    /**
     * 获取日期解析成  格式  2020-12-12 上午/下午
     */
    public static String getForenoonOfDate(Date date){
        if(date==null){
            return  null;
        }
        String  day=getDayString(date);//获取日期 2020-12-12
        Integer hour=getHour(date);
        String  forenoon=hour!=null?hour<=12&&hour>=6?"上午":"下午":"";
        return  day+" "+forenoon;
    }

    /**
     * Date转LocalDate
     * @param date
     */
    public static LocalDate date2LocalDate(Date date) {
        if(null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 距离现在多少天 传入空默认0天
     * @param date
     * @return
     */
    public static Long dateNowDiff(Date date){
        if(date==null){
            return 0L;
        }
        return ChronoUnit.DAYS.between(date2LocalDate(date), LocalDate.now());
    }

    /**
     * 获取日期解析成  格式  上午/下午
     */
    public static String getMorningOrAfternoonByDate(Date date){
        if(date==null){
            return  null;
        }
        String  day=getDayString(date);//获取日期 2020-12-12
        Integer hour=getHour(date);
        String  forenoon=hour!=null?hour<=12&&hour>=6?"上午":"下午":"";
        return  forenoon;
    }
}
