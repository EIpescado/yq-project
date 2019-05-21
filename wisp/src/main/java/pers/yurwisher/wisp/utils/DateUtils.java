package pers.yurwisher.wisp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 * @author yq on 2017/01/11 11:19.
 */
public class DateUtils {

    private DateUtils() {
    }

    //年月(yyyy-MM)
    public static final String YM_DATE_PATTERN = "yyyy-MM";

    //时分秒(HH:mm:ss)
    public static final String HMS_DATE_PATTERN = "HH:mm:ss";

     //短日期格式 (yyyy-MM-dd)
    public static final String SHORT_DATE_PATTERN = "yyyy-MM-dd";

    //短日期格式 (yyyy-MM-dd)
    public static final String SHORT_DATE_PATTERN2 = "yyyy/MM/dd";

    //短日期格式 (yyyy-MM-dd)
    public static final String SHORT_DATE_PATTERN3 = "yyyyMMdd";

    /**长日期格式 (yyyy-MM-dd HH:mm:ss)小写hh 12小时制*/
    public static final String LONG_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**长日期格式 (yyyy-MM-dd HH:mm:ss)小写hh 12小时制*/
    public static final String LONG_DATE_PATTERN2 = "yyyy/MM/dd HH:mm:ss";

    /**精确到毫秒日期格式*/
    public static final String MS_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    /**汇率时间格式*/
    public static final String RATE_DATE_PATTERN = "yyyy.MM.dd HH:mm:ss";

    /**日期格式,精确到分*/
    public static final String YMDHM_DATE_PATTERN = "yyyy-MM-dd HH:mm";

    /**汇率时间格式化类 SimpleDateFormat 线程不安全*/
    private static  final ThreadLocal<DateFormat> RATE_DATE_FORMAT_TL= new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(RATE_DATE_PATTERN);
        }
    };

    //时分秒格式化类
    private static  final ThreadLocal<DateFormat> HMS_DATE_FORMAT_TL = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(HMS_DATE_PATTERN);
        }
    };

    //年月格式化类
    private static  final ThreadLocal<DateFormat> YM_DATE_FORMAT_TL = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(YM_DATE_PATTERN);
        }
    };

    //短格式化类
    private static  final ThreadLocal<DateFormat> SHORT_DATE_FORMAT_TL = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(SHORT_DATE_PATTERN);
        }
    };

    //短格式化类
    private static  final ThreadLocal<DateFormat> SHORT_DATE_FORMAT_TL2 = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(SHORT_DATE_PATTERN2);
        }
    };

    //短格式化类
    private static  final ThreadLocal<DateFormat> SHORT_DATE_FORMAT_TL3 = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(SHORT_DATE_PATTERN3);
        }
    };

    //长格式化类
    private static  final ThreadLocal<DateFormat> LONG_DATE_FORMAT_TL = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(LONG_DATE_PATTERN);
        }
    };

    //长格式化类
    private static  final ThreadLocal<DateFormat> LONG_DATE_FORMAT_TL2 = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(LONG_DATE_PATTERN2);
        }
    };

    //长格式化类,精确到毫秒
    private static  final ThreadLocal<DateFormat> MS_DATE_FORMAT_TL = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(MS_DATE_PATTERN);
        }
    };

    //长格式化类,精确到毫秒
    private static  final ThreadLocal<DateFormat> YMDHM_DATE_FORMAT_TL = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(YMDHM_DATE_PATTERN);
        }
    };

    //一分钟 毫秒数
    public static final long MS_ONE_MINUTE = 60 * 1000;
    //一小时 毫秒数
    public static final long MS_ONE_HOUR = 60 * MS_ONE_MINUTE;
    //一天 毫秒数
    public static final long MS_ONE_DAY = 24 * 60 * MS_ONE_MINUTE;

    /**
     * 汇率日期转化
     */
    public static Date parseRateDate(String date) {
        return parse2Date(date,RATE_DATE_FORMAT_TL);
    }

    /**
     * 字符串转Date yyyy-MM-dd HH:mm:ss
     */
    public static Date parse2LongDate(String date) {
        return  parse2Date(date,LONG_DATE_FORMAT_TL);
    }

    /**
     * 字符串转Date yyyy-MM-dd HH:mm:ss
     */
    public static Date parse2LongDate2(String date) {
        return  parse2Date(date,LONG_DATE_FORMAT_TL2);
    }

    /**
     * 字符串转Date yyyy-MM-dd
     */
    public static Date parse2Date(String date) {
        return  parse2Date(date,SHORT_DATE_FORMAT_TL);
    }

    /**
     * 字符串转Date yyyy/MM/dd
     */
    public static Date parse2Date2(String date) {
        return  parse2Date(date,SHORT_DATE_FORMAT_TL2);
    }

    /**
     * 字符串转Date yyyyMMdd
     */
    public static Date parse2Date3(String date) {
        return  parse2Date(date,SHORT_DATE_FORMAT_TL3);
    }


    /**
     * 字符串转Date yyyy-MM-dd HH:mm 精确到分钟
     */
    public static Date parse2DateAccurate2Min(String date) {
        return  parse2Date(date,YMDHM_DATE_FORMAT_TL);
    }

    /**
     * 字符串转Date yyyy-MM-dd HH:mm:ss Sss 精确到毫秒
     */
    public static Date parse2DateAccurate2Ms(String date) {
        return  parse2Date(date,MS_DATE_FORMAT_TL);
    }

    /**
     * 按指定 formate 转化为日期
     */
    private static Date parse2Date(String date ,ThreadLocal<DateFormat> format){
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        try {
            return format.get().parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 日期转化 yyyy-MM-dd
     */
    public static String parse2String(Date date) {
        if (date == null) {
            return null;
        }
        return SHORT_DATE_FORMAT_TL.get().format(date);
    }

    public static String parse2yyyyMMdd(Date date) {
        if (date == null) {
            return null;
        }
        return SHORT_DATE_FORMAT_TL3.get().format(date);
    }


    /**
     * 日期转化 yyyy-MM-dd HH:mm:ss
     */
    public static String parse2LongString(Date date) {
        if (date == null) {
            return null;
        }
        return LONG_DATE_FORMAT_TL.get().format(date);
    }

    /**
     * 日期转化 yyyy-MM-dd HH:mm 精确到分
     */
    public static String parse2StringAccurate2Min(Date date) {
        if (date == null) {
            return null;
        }
        return YMDHM_DATE_FORMAT_TL.get().format(date);
    }

    /**
     * 获取日期年份
     */
    public static String getYear(Date date) {
        return Integer.toString(getNumByType(date,Calendar.YEAR));
    }

    /**
     * 获取日期年份
     */
    public static int getDateYear(Date date) {
        return getNumByType(date,Calendar.YEAR);
    }

    /**
     * 今年的第几周
     */
    public static int getWeekOfYear(Date date){
        return getNumByType(date,Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取日期月份
     */
    public static String getMonth(Date date) {
        int month = getNumByType(date,Calendar.MONTH) + 1 ;
        return  month < 10 ? "0" + month :Integer.toString(month) ;
    }

    /**
     * 获取日期月份
     */
    public static int getDateMonth(Date date) {
        return  getNumByType(date,Calendar.MONTH) + 1 ;
    }

    /**
     * 获取日期天
     */
    public static String getDay(Date date) {
        int day =  getNumByType(date,Calendar.DAY_OF_MONTH);
        return  day < 10 ? "0" + day :Integer.toString(day) ;
    }

    /**
     * 获取日期天
     */
    public static int getDateDay(Date date) {
        return  getNumByType(date,Calendar.DAY_OF_MONTH) ;
    }

    /**
     * 获取日期小时
     */
    public static int getDateHour(Date date) {
        return getNumByType(date,Calendar.HOUR_OF_DAY);
    }

    private static int getNumByType(Date date ,int type){
        if (date == null ) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = getCalendar(date);
        return calendar.get(type) ;
    }

    /**
     * 是否是工作日 (周一 至 周五)
     */
    public static boolean isWorkDay(Date date){
        //周是从周天(1)开始
        int day_of_week =  getNumByType(date,Calendar.DAY_OF_WEEK);
        return day_of_week > 1 && day_of_week < 7 ;
    }

    /**
     * 是否是同一天
     */
    public static boolean isSameDay(final Date date1, final Date date2){
        if (date1 == null  || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        final Calendar cal1 = getCalendar(date1);
        final Calendar cal2 = getCalendar(date2);
        return isSameDay(cal1, cal2);
    }

    public static boolean isSameDay(final Calendar cal1, final Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }//地区
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * 日期增加分钟
     */
    public static Date addMinutes(final Date date, final int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    /**
     * 日期增加小时
     */
    public static Date addHours(final Date date, final int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * 日期增加天数
     */
    public static Date addDays(final Date date, final int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * 日期增加月
     */
    public static Date addMonths(final Date date, final int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    /**
     * 日期增加周
     */
    public static Date addWeeks(final Date date, final int amount) {
        return add(date, Calendar.WEEK_OF_MONTH, amount);
    }

    /**
     * 日期增加年
     */
    public static Date addYears(final Date date, final int amount) {
        return add(date, Calendar.YEAR, amount);
    }


    private static Date add(final Date date, final int calendarField, final int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        final Calendar c = getCalendar(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     * 计算指定两个时间之间的相差分钟数
     */
    public static int minutesBetween(Date before, Date after) {
        if (before == null || after == null ) {
            throw new IllegalArgumentException("The date must not be null");
        }
        long dms = after.getTime() - before.getTime();
        return (int) (dms / MS_ONE_MINUTE);
    }

    /**
     * 计算指定两个时间之间的相差小时数
     */
    public static int hoursBetween(Date before, Date after) {
        if (before == null || after == null ) {
            throw new IllegalArgumentException("The date must not be null");
        }
        long dms = after.getTime() - before.getTime();
        return (int) (dms / MS_ONE_HOUR);
    }

    /**
     * 计算指定两个时间之间的相差天数
     */
    public static int daysBetween(Date before, Date after) {
        if (before == null || after == null ) {
            throw new IllegalArgumentException("The date must not be null");
        }
        long dms = after.getTime() - before.getTime();
        return (int) (dms / MS_ONE_DAY);
    }

    /**
     * 计算指定两个时间之间的相差月份
     */
    public static int monthsBetween(Date before,Date after) {
        return ( Integer.valueOf(getYear(after)) - Integer.valueOf(getYear(before) )) * 12 +
                Integer.valueOf(getMonth(after)) - Integer.valueOf(getMonth(before)) ;
    }

    /**
     * 获取日期的时分秒
     */
    public static String getDateHms(Date date){
        if(date == null){
            return  "";
        }
       return HMS_DATE_FORMAT_TL.get().format(date) ;
    }

    /**
     * 获取日期的年月
     */
    public static String getDateYm(Date date){
        if(date == null){
            return  "";
        }
        return YM_DATE_FORMAT_TL.get().format(date) ;
    }

    /**
     * 获取指定日期的日历对象
     */
    public static Calendar getCalendar(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c ;
    }

    /**
     * 获取指定 年月日 时间 00:00:00
     */
    public static Date getDate(int year, int month, int day) {
        Calendar c = new GregorianCalendar(year,month - 1,day);
        return c.getTime();
    }
    
    /**
     *	获取当天时间:  yyyy:MM:dd 00:00:00
     * @return
     */
    public static Date getTodayZero() {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return zero;
    }

    /**
     * 获取指定 年月日 分时秒 时间
     */
    public static Date getDate(int year, int month, int day ,int hourOfDay,int minute, int second ) {
        GregorianCalendar c = new GregorianCalendar(year,month - 1,day,hourOfDay,minute,second);
        return c.getTime();
    }

    /**
     * 修改年份
     */
    public static Date changeYear(Date date , int year){
        return set(date,Calendar.YEAR,year);
    }

    /**
     * 修改月份
     */
    public static Date changeMonth(Date date , int month){
        return set(date,Calendar.MONTH,month - 1 );
    }

    /**
     * 修改天
     */
    public static Date changeDay(Date date , int day){
        return set(date,Calendar.DAY_OF_MONTH,day);
    }

    /**
     * 修改小时
     */
    public static Date changeHour(Date date , int hourOfDay){
        return set(date,Calendar.HOUR_OF_DAY,hourOfDay);
    }

    /**
     * 修改分
     */
    public static Date changeMinute(Date date , int minute){
        return set(date,Calendar.MINUTE,minute);
    }

    /**
     * 修改秒
     */
    public static Date changeSecond(Date date , int second){
        return set(date,Calendar.SECOND,second);
    }

    private static Date set(final Date date, final int calendarField, final int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        // getInstance() returns a new object, so this method is thread safe.
        final Calendar c = Calendar.getInstance();
        //宽松模式关闭  若填写日期不符合规范 如 2 月 30 天则抛出异常 默认开启 则自动变为3.2
//        c.setLenient(false);
        c.setTime(date);
        c.set(calendarField, amount);
        return c.getTime();
    }

    /**
     * 日期对应月份天数
     */
    public static int dayNumOfMonth(Date date){
        return getCalendar(date).getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 日期对应年天数
     */
    public static int dayNumOfYear(Date date){
        return getCalendar(date).getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    /**
     * 是否是闰年
     */
    public static boolean isLeapYear(int year){
        GregorianCalendar c = new GregorianCalendar();
        return  c.isLeapYear(year);
    }

}