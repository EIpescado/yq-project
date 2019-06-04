package pers.yurwisher.wisp.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author yq
 * @date 2018/11/16 14:37
 * @description jdk8 time包工具
 * @since V1.0.0
 */
public class TimeUtils {

    private static  final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static  final DateTimeFormatter YYYY_MM_DD_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static  final DateTimeFormatter YYYYMMDD_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private TimeUtils(){

    }

    public static String parse(LocalDateTime dateTime){
        if(dateTime != null){
           return dateTime.format(DEFAULT_FORMATTER);
        }
        return null;
    }

    public static String parseYMD(LocalDateTime dateTime){
        if(dateTime != null){
            return dateTime.format(YYYY_MM_DD_FORMATTER);
        }
        return null;
    }

    public static String parseYYYYMMDD(LocalDateTime dateTime){
        if(dateTime != null){
            return dateTime.format(YYYYMMDD_FORMATTER);
        }
        return null;
    }

    public static LocalDateTime format(String s){
       return LocalDate.parse(s,YYYY_MM_DD_FORMATTER).atStartOfDay();
    }

    public static long getTimeMillis(LocalDateTime dateTime){
        return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

}
