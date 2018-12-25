/**
 * Project Name:linc-common
 * File Name:DateUtil.java
 * Package Name:com.linc.common.util
 * Date:2017年3月14日下午2:20:53
 * Copyright @ 2010-2017 上海企垠信息科技有限公司  All Rights Reserved.
 */
package com.imooc.jay.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ClassName:DateUtil <br/>
 * Description: 日期处理工具类. <br/>
 * Date: 2018-07-10 22:37:13 <br/>
 */
public class DateUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * addDay:(日期增加). <br/>
     *
     * @param date
     * @param counts
     * @return
     */
    public static Date addDay(final Date date, final int counts) {
        Calendar t = new GregorianCalendar();
        t.setTime(date);
        t.add(Calendar.DATE, counts);
        return t.getTime();
    }

    /**
     * compareDate:比较两个日期的大小 <br/>
     * 适用条件：比较两个日期的大小<br/>
     * 执行流程：工具类调用<br/>
     *
     * @param firstDate 第一个日期值
     * @param lastDate  第二个日期值
     * @return boolean 布尔值
     */
    public static boolean compareDate(Date firstDate, Date lastDate) {
        if (firstDate.getTime() == lastDate.getTime()) {
            return true;
        }
        return false;
    }

    /**
     * compareDate:比较两个日期的大小,firstDate大于lastDate返回true;反之返回false. <br/>
     *
     * @param firstDate
     * @param lastDate
     * @return
     */
    public static boolean compareDate(String firstDate, String lastDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        boolean bol = false;
        try {
            Date date = formatter.parse(firstDate);
            Date compareDate = formatter.parse(lastDate);
            if (date.getTime() > compareDate.getTime()) {
                bol = true;
            } else {
                bol = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bol;

    }

    /**
     * convertLong2Day:(时间换算成天数). <br/>
     *
     * @param interMiliSec
     * @return
     */
    public static double convertLong2Day(final long interMiliSec) {
        return interMiliSec / 24.0 / 60 / 60 / 1000;
    }

    /**
     * dateAdd:在传入的Date上加上i天. <br/>
     *
     * @param str
     * @param i
     * @return
     * @throws ParseException
     */
    public static String dateAdd(String str, int i) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = null;
        try {
            myDate = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(myDate);
        c.add(Calendar.DATE, i);
        myDate = c.getTime();
        return formatter.format(myDate);

    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
            smdate = sdf.parse(sdf.format(smdate));

            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24) + 1;

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 字符串的日期格式的计算
     *
     * @param smdate "2016-01-01" 较小的时间
     * @param bdate  "2016-01-02" 较大的时间
     */
    public static int daysBetween(String smdate, String bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        long between_days = 0;
        try {
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            between_days = (time2 - time1) / (1000 * 3600 * 24) + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * formatDate:格式化日期，返回yyyy-MM-dd<br/>
     * 适用条件：格式化日期，返回yyyy-MM-dd<br/>
     * 执行流程：内部调用<br/>
     * 注意事项：字符串必须是日期格式<br/>
     *
     * @param date 日期
     * @return String 日期字符串
     */
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT);
        return sdf.format(date);
    }

    /**
     * formatTime:格式化时间，返回HH:mm:ss <br/>
     * 适用条件：格式化时间，返回HH:mm:ss<br/>
     * 执行流程：内部调用<br/>
     * 注意事项：字符串必须是日期格式<br/>
     *
     * @param date 日期
     * @return String 时间字符串
     */
    private static String formatTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.TIME_FORMAT);
        return sdf.format(date);
    }

    /**
     * getControlDate:(这里用一句话描述这个方法的作用)获得年份数据. <br/>
     * 执行流程：外部调用.<br/>
     *
     * @param before 获得当前年份之前多少年的年份
     * @param alter  获得当前年份之后多少的年份
     * @return 所需的年份的list
     */
    public static List<String> getControlDate(int before, int alter) {
        // 获取日历对象
        Calendar calendar = Calendar.getInstance();
        // 获得当前年份
        int year = calendar.get(Calendar.YEAR);
        // 创建年份的list对象
        List<String> years = new ArrayList<String>();
        // 判断是否需要获得当前日期之前的年份
        if (before > 0) {
            int howBefore = before; // 记录需要当前年份多少之前多少年的年份
            int i;
            for (i = 0; i < howBefore; i++) {
                years.add(String.valueOf(year - before)); // 当前年份之前的年份
                before--;
            }
        }
        // 当前年份
        years.add(String.valueOf(year));
        // 判断是否需要获得当前日期之前后的年份
        if (alter > 0) {
            int j;
            for (j = 0; j < alter - 1; j++) {
                years.add(String.valueOf(year + 1)); // 当前月份之后的年份
                year++;
            }
        }
        return years;
    }

    /**
     * getCurrentDate:得到系统当日日期，其中日期的格式为：yyyy-MM-dd，例如：2005-11-01 <br/>
     * 执行流程：外部调用<br/>
     *
     * @return String 时间字符串
     */
    public static String getCurrentDate() {
        return formatDate(new Date());
    }

    /**
     * getCurrentDate:得到指定格式的系统当日日期
     *
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        return formatDate.format(new Date());
    }

    /**
     * getCurrentDateTime:获取当前日期时间，格式为yyyy-MM-dd HH:mm:ss. <br/>
     *
     * @return
     * @author:guchenlong Date: 2017年4月26日 上午11:16:30
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat formatDate = new SimpleDateFormat(DATE_TIME_FORMAT);
        return formatDate.format(new Date());
    }

    /**
     * getCurrentTime:得到系统当日日期，其中日期的格式为：HH:mm:ss，例如：11:20:03 <br/>
     * 执行流程：外部调用<br/>
     *
     * @return String 时间字符串
     */
    public static String getCurrentTime() {
        return formatTime(new Date());
    }

    /**
     * getDayOfMonth:获取当前月份的天数
     *
     * @return
     */
    public static int getDayOfMonth() {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        return day;
    }

    /**
     * getDayOfMonth:获取当前月份的天数
     *
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        aCalendar.setTime(date);
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        return day;
    }

    /**
     * getIntervalDay:(计算日期间的相隔天数). <br/>
     *
     * @param startDay
     * @param endDay
     * @return
     */
    public static int getIntervalDay(final Date startDay, final Date endDay) {
        Long intervalMillis = endDay.getTime() - startDay.getTime();
        Long dayMillis = 1000L * 60L * 60L * 24L;
        if ((intervalMillis % dayMillis) != 0) {
            return (int) (convertLong2Day(intervalMillis) + 1L);
        } else {
            return (int) (convertLong2Day(intervalMillis));
        }
    }

    public static int getIntervalDay(final String startDay, final String endDay, final String format)
            throws RuntimeException {
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);
            Long intervalMillis = df.parse(endDay).getTime() - df.parse(startDay).getTime();
            Long dayMillis = 1000L * 60L * 60L * 24L;
            if ((intervalMillis % dayMillis) != 0) {
                return (int) (convertLong2Day(intervalMillis) + 1L);
            } else {
                return (int) (convertLong2Day(intervalMillis));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * getMonth:获取当前月份. <br/>
     * 执行流程：外部调用.<br/>
     *
     * @return int 月份
     */
    public static int getMonth() {
        // 获取日历对象
        Calendar calendar = Calendar.getInstance();
        // 获取当前月份
        int month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * getQuarte:获取当前季度. <br/>
     * 执行流程：外部调用.<br/>
     *
     * @return int 季度
     */
    public static int getQuarte() {
        // 获取日历对象
        Calendar calendar = Calendar.getInstance();
        int quarte = calendar.get(Calendar.MONTH) / 3;
        return (quarte + 1);
    }

    /**
     * getStringDate:将传入时间已指定的格式输出. <br/>
     * 传入参数date为Date类型.<br/>
     * 执行流程：外部调用.<br/>
     * 参数formatString一定要为.<br/>
     *
     * @param date         时间
     * @param formatString 所需的格式 如:yyyyMMdd、yyyy-MM-dd等
     * @return String 时间字符
     */
    public static String getStringDate(Date date, String formatString) {
        return new SimpleDateFormat(formatString).format(date);
    }

    public static int getYear() {
        // 获取日历对象
        Calendar calendar = Calendar.getInstance();
        // 获取当前月份
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    /**
     * betweenDate:比较日期是否介于两者之间 <br/>
     * 适用条件：比较日期是否介于两者之间<br/>
     * 执行流程：工具类调用<br/>
     *
     * @param betweenDate 比较日期
     * @param toStartDate 开始日期
     * @param toEndDate   结束日期
     * @return boolean 布尔值
     */
    public static boolean isBetweenDates(Date betweenDate, Date toStartDate, Date toEndDate) {
        if (toStartDate.getTime() <= betweenDate.getTime()
                && betweenDate.getTime() <= toEndDate.getTime()) {
            return true;
        }
        return false;
    }

    /**
     * compareDate:比较两个日期是否相等. <br/>
     *
     * @param firstDate
     * @param lastDate
     * @return
     */
    public static boolean isEqual(String firstDate, String lastDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        boolean bol = false;
        try {
            Date date = formatter.parse(firstDate);
            Date compareDate = formatter.parse(lastDate);
            if (date.getTime() == compareDate.getTime()) {
                bol = true;
            } else {
                bol = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bol;
    }

    /**
     * praseDate:格式化日期，将String格式化成Date型 <br/>
     * 适用条件：转换日期类型<br/>
     * 执行流程：外部调用<br/>
     * 使用方法：传入String日期<br/>
     *
     * @param date 日期
     * @return Date 日期型格式
     * @throws ParseException 转换异常
     */
    public static Date praseDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.parse(date);
    }

    /**
     * sysTime:系统当前时间，返回yyyy年MM月dd日格式 <br/>
     * 适用条件：主页面的时间显示<br/>
     * 执行流程：外部方法调用<br/>
     * 使用方法：外部方法调用<br/>
     *
     * @return String 返回格式化之后的时间
     */
    public static String sysTime() {
        SimpleDateFormat loginFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return loginFormat.format(new Date());
    }

    /*
     * 计算2017-05和2017-09有几个月
     */
    public static int getMonthCount(String beginDate, String endDate) {
        String[] str1 = beginDate.split("-");
        String[] str2 = endDate.split("-");
        int a = Integer.parseInt(str1[0]) * 12 + Integer.parseInt(str1[1]);
        int b = Integer.parseInt(str2[0]) * 12 + Integer.parseInt(str2[1]);
        return b - a + 1;
    }

    /*
     * 在原有2017-05上加1月
     */
    public static String addOneMonth(String beginDate) {
        String[] str1 = beginDate.split("-");
        int a = Integer.parseInt(str1[0]) * 12 + Integer.parseInt(str1[1]) + 1;
        int b = a / 12;
        int c = a % 12;
        if (c < 10) {
            return b + "-0" + c;
        } else {
            return b + "-" + c;
        }
    }

    /**
     * 时间戳转日期
     *
     * @param ms
     * @return
     */
    public static String transForDateInChinese(Integer ms) {
        return transForDate(ms, "yyyy年MM月dd日 HH:mm:ss");
    }

    public static String transForDate(Integer ms) {
        return transForDate(ms, DATE_TIME_FORMAT);
    }

    public static String transForDate(Integer ms, String format) {
        String str = "";
        if (ms != null) {
            long msl = (long) ms * 1000;
            SimpleDateFormat sdf = new SimpleDateFormat(format);

            if (ms != null) {
                try {
                    str = sdf.format(msl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }

    public static Date getDateCurrentDateTime(){
        long ts = (System.currentTimeMillis() / 1000) * 1000;
        return new Date(ts);
    }

    //获取当前月份第一天
    public static Date getFirstDayOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int days = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH,days);
        return calendar.getTime();
    }

    /**
     * 获取这个月的最后一天
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH);
        Integer maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.clear();
        calendar.set(year,month,maxDay,23,59);

        return calendar.getTime();

    }

    //时间转date
    public static Date transForm(Integer time) {
        try {
            DateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTimeStr = ymdhmsFormat.format(time.longValue() * 1000);
            Date nowTimeDate = ymdhmsFormat.parse(nowTimeStr);
            return nowTimeDate;
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getMinute(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE) + calendar.get(Calendar.HOUR_OF_DAY)*60;
    }


    public static Integer getWeek(Integer time){
        Date date = transForm(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) -1 ;
    }

    public static Integer getWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) -1 ;
    }

    /**
     * 获取精确到秒的时间戳
     * @param date
     * @return
     */
    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime()/1000);
        return Integer.valueOf(timestamp);
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateString 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Long date2TimeStamp(String dateString, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateString).getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 将分钟转为 HH:mm
     * @param minute
     * @return
     */
    public static String minuteToTimeStr(Integer minute) {
        Integer hourInt = minute / 60;
        Integer minuteInt = minute % 60;

        String hourStr = hourInt < 10 ? "0" + hourInt : "" + hourInt;
        String minuteStr = minuteInt < 10 ? "0" + minuteInt : "" + minuteInt;

        return hourStr + ":" + minuteStr;
    }


    public static Date getDate(String date,String format) {
        if(format== null || "".equals(format)){
            format = "yyyy-MM-dd";
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date dd = null;
        try {
            dd = dateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dd;
    }

    public static String longTransString(long time){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time * 1000);
        return sdf.format(date);
    }

    public static String longTransStringHour(long time){
        SimpleDateFormat sdf= new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(time * 1000);
        return sdf.format(date);
    }
}
