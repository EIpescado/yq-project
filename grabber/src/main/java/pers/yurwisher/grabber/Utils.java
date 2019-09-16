package pers.yurwisher.grabber;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;

/**
 * @author yq
 * @date 2018/12/11 14:58
 * @description utils
 * @since V1.0.0
 */
public class Utils {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    private Utils() {

    }

    public static BigDecimal string2BigDecimal(String str) {
        try {
            if (str == null || str.length() == 0) {
                return null;
            }
            return new BigDecimal(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static BigDecimal divideOneHundred(BigDecimal x) {
        return x != null ? x.divide(ONE_HUNDRED, 6, BigDecimal.ROUND_HALF_UP) : null;
    }

    public static LocalDateTime parseRateDate(String date, DateTimeFormatter dateTimeFormatter) {
        if (isNotEmpty(date)) {
            return LocalDateTime.parse(date, dateTimeFormatter);
        }
        return null;
    }

    public static boolean isNotEmpty(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static boolean isNotEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }
    public static int size(Collection collection) {
        return isNotEmpty(collection) ? collection.size() : 0;
    }

    public static long toEpochMilli(LocalDateTime dateTime) {
        return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static long toEpochMilli(String dateTime, DateTimeFormatter dateTimeFormatter) {
        return toEpochMilli(LocalDateTime.parse(dateTime, dateTimeFormatter));
    }

    public static String null2EmptyWithTrimNew(String x){
        return isNotEmpty(x) ? x : "";
    }

    public static String null2EmptyWithTrimNew(Object x){
        return x != null && isNotEmpty(x.toString()) ? x.toString() : "";
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.size() == 0 ;
    }

    public static boolean isNotEmpty(Map map) {
        return map != null && map.size() > 0;
    }

    public static void mkDir(File file) {
        if (file.getParentFile().exists()) {
            file.mkdir();
        } else {
            mkDir(file.getParentFile());
            file.mkdir();
        }
    }
}
