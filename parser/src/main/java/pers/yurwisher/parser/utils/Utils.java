package pers.yurwisher.parser.utils;

import java.util.Collection;

/**
 * @author yq
 * @date 2019/01/18 10:23
 * @description
 * @since V1.0.0
 */
public class Utils {

    public static boolean isNotEmpty(String x){
        return x != null && x.trim().length() > 0 ;
    }

    public static boolean isNotEmpty(Collection x){
        return x != null && !x.isEmpty() ;
    }

    /**
     * 字符串去左右空格
     */
    public static String null2EmptyWithTrimNew(Object s) {

        if (s == null || "NULL".equalsIgnoreCase(s.toString())) {
            return "";
        } else {
            return s.toString().trim();
        }
    }
}
