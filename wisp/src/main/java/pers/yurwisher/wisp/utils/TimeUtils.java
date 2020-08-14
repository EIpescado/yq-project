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

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter YYYY_MM_DD_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter YYYY_MM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    private static final DateTimeFormatter YYYYMMDD_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private static final DateTimeFormatter YYYY_SLASH_M_SLASH_D_FORMATTER = DateTimeFormatter.ofPattern("yyyy/M/d");

    private static final DateTimeFormatter YYYY_HYPHEN_M_HYPHEN_D_FORMATTER = DateTimeFormatter.ofPattern("yyyy-M-d");

    private TimeUtils() {

    }

    public static String parse(LocalDateTime dateTime) {
        if (dateTime != null) {
            return dateTime.format(DEFAULT_FORMATTER);
        }
        return null;
    }

    public static String parseYMD(LocalDateTime dateTime) {
        if (dateTime != null) {
            return dateTime.format(YYYY_MM_DD_FORMATTER);
        }
        return null;
    }

    public static String parseYM(LocalDateTime dateTime) {
        if (dateTime != null) {
            return dateTime.format(YYYY_MM_FORMATTER);
        }
        return null;
    }

    public static String parseYMD(LocalDate dateTime) {
        if (dateTime != null) {
            return dateTime.format(YYYY_MM_DD_FORMATTER);
        }
        return null;
    }

    public static String parseYM(LocalDate dateTime) {
        if (dateTime != null) {
            return dateTime.format(YYYY_MM_FORMATTER);
        }
        return null;
    }

    public static String parseYYYYMMDD(LocalDateTime dateTime) {
        if (dateTime != null) {
            return dateTime.format(YYYYMMDD_FORMATTER);
        }
        return null;
    }

    public static LocalDateTime formatYMD(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return LocalDate.parse(s, YYYY_MM_DD_FORMATTER).atStartOfDay();
    }

    public static LocalDateTime formatExcelYMD(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return LocalDate.parse(s, YYYY_SLASH_M_SLASH_D_FORMATTER).atStartOfDay();
    }

    public static LocalDateTime formatExcelNormalYMD(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return LocalDate.parse(s, YYYY_HYPHEN_M_HYPHEN_D_FORMATTER).atStartOfDay();
    }

    public static LocalDateTime formatYMDHMS(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return LocalDate.parse(s, DEFAULT_FORMATTER).atStartOfDay();
    }

    public static LocalDate formatDate(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return LocalDate.parse(s, YYYY_MM_DD_FORMATTER);
    }

    public static long getTimeMillis(LocalDateTime dateTime) {
        return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 从身份证中提取出生日期
     * @param idNumber 身份证号
     * @return 出生日期
     */
    public static LocalDateTime getBirthdayFromIdNumber(String idNumber){
        String p = StringUtils.null2EmptyWithTrimNew(idNumber);
        if(StringUtils.isEmpty(p)){
            return null;
        }
        int length = p.length();
        if(length == 15 || length == 18){
            try{
                return LocalDate.parse(p.substring(6,14),YYYYMMDD_FORMATTER).atStartOfDay();
            }catch (Exception e){
                return null;
            }
        }
        return null;
    }

}
