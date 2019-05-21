package pers.yurwisher.wisp.utils;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yq on 2017/01/04 17:15.
 */
public class StringUtils {

    /**匹配所有特殊字符的正则表达式*/
    public static final String SPECIAL_CHARACTER_REGEX = "[`~!@#$%^&*()\\-+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）—_+|{}【】‘；：”“’。，、\"？\\s]";

    public static final Pattern SPECIAL_CHARACTER_PATTERN = Pattern.compile(SPECIAL_CHARACTER_REGEX) ;

    /**中文正则*/
    public static final String CHINESE_REGEX = "[\u4e00-\u9fa5]" ;

    public static final Pattern CHINESE_REGEX_PATTERN = Pattern.compile(CHINESE_REGEX) ;

    /**数字正则*/
    public static final String NUMBER_REGEX = ".*\\d+.*" ;

    public static final Pattern NUMBER_REGEX_PATTERN = Pattern.compile(NUMBER_REGEX) ;

    /**匹配括号中间内容*/
    private static final Pattern BRACKET_CONTENT_PATTERN = Pattern.compile("(?<=\\()(.+?)(?=\\))");

    public static final char UNDERLINE = '_';


    /**纯数字正则*/
    public static final String PURE_NUMBER_REGEX = "[0-9]*" ;

    public static final Pattern PURE_NUMBER_REGEX_PATTERN = Pattern.compile(PURE_NUMBER_REGEX) ;

    private StringUtils() {
    }

    /**
     * 二进制转字符串
     */
    static String byte2hex(byte[] b) {
        String hs = "" ;
        String stmp = "" ;
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF)) ;
            if (stmp.length() == 1){
                hs = hs + "0" + stmp ;
            } else{
                hs = hs + stmp ;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 字符串去左右空格
     */
    public static  String null2EmptyWithTrim(String s) {
        if (s == null) {
            return "";
        }else{
            return s.trim();
        }
    }

    /**
     * 字符串去左右空格
     */
    public static  String null2EmptyWithTrimNew(Object s)
    {

        if (s == null || "NULL".equalsIgnoreCase(s.toString())) {
            return "";
        } else {
            return s.toString().trim();
        }
    }

    /**
     * 字符串是否为空
     */
    public static  boolean isEmpty(String foo) {
        return (foo == null || foo.trim().length() == 0);
    }

    /**
     * 字符串是否不为空
     */
    public static  boolean isNotEmpty(String foo) {
        return (null != foo && foo.trim().length() > 0);
    }

    /**
     * 计算一个字符串的长度，汉字当成两个字符计算，ascii英文字符当成一个。
     * @param aStr 要计算长度的目标字符串
     * @return 计算出的长度
     */
    public static int lengthOfCN(String aStr) {
        char c;
        int length = 0;
        if(isNotEmpty(aStr)){
            for (int i = 0; i < aStr.length(); i++) {
                c = aStr.charAt(i);
                if (c >= 127) {
                    length += 2;
                }else{
                    length += 1;
                }
            }
        }
        return length;
    }

    /**
     * 字符串首字母大写或小写
     */
    public static String firstLetterUpperOrLower(String str,boolean isLowerCase) {
        if (isNotEmpty(str)){
            if (str.length() == 1) {
                return isLowerCase ? str.toLowerCase() : str.toUpperCase() ;
            }else{
                String first = str.substring(0, 1).toLowerCase();
                first = isLowerCase ? first.toLowerCase() : first.toUpperCase() ;
                return (first + str.substring(1));
            }
        }
        return str;
    }

    /**
     * 去除字符串所有特殊字符
     */
    public static String removeAllSpecialChar(String str) {
        Matcher m = SPECIAL_CHARACTER_PATTERN.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 判断str是否在strArr中
     */
    public static boolean strInArray(String str, String[] strArr) {
        for(String s : strArr){
            if (str.equals(s)){
                return true;
            }
        }
        return false;
    }

    /**
     * 按分隔符分隔字符串
     */
    @Deprecated
    public static String[] split(String text, String separator) {

        StringTokenizer st = new StringTokenizer(text, separator);
        //分隔符数量大小的字符串数组
        String[] values = new String[st.countTokens()];
        int pos = 0;
        //是否还有分隔符
        while (st.hasMoreTokens()){
            //返回从当前位置到下一个分隔符的字符串
            values[pos++] = st.nextToken();
        }
        return values;
    }

    /**
     * 判断字符串中是否包含中文
     */
    public static boolean isContainChinese(String str) {
        if(isNotEmpty(str)){
            Matcher m = CHINESE_REGEX_PATTERN.matcher(str);
            return  m.find() ;
        }
        return false;
    }

    /**
     * 获取字符串中所有中文
     */
    public static String getAllChineseInStr(String str){
        return  str.replaceAll("[^\u4e00-\u9fa5]", "") ;
    }

    /**
     * 获取括号内内容
     * @param str
     */
    public static  String getBracketContent(String str){
        Matcher matcher = BRACKET_CONTENT_PATTERN.matcher(str);
        if(matcher.find()){
            return  matcher.group(0);
        }else {
            return "";
        }
    }

    /**
     * 左补全字符
     * @param w84PaddingStr 需要补全的字符
     * @param digit 补全后字符的位数
     * @param paddingStr 补全使用的字符
     */
    public static String leftPadding(String w84PaddingStr,int digit,String paddingStr){
        w84PaddingStr = null2EmptyWithTrim(w84PaddingStr);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digit - w84PaddingStr.length(); i++) {
            sb.append(paddingStr);
        }
        sb.append(w84PaddingStr);
        return sb.toString();
    }

    /**
     * 右补全字符
     * @param w84PaddingStr 需要补全的字符
     * @param digit 补全后字符的位数
     * @param paddingStr 补全使用的字符
     */
    public static String rightPadding(String w84PaddingStr,int digit,String paddingStr){
        w84PaddingStr = null2EmptyWithTrim(w84PaddingStr);
        StringBuffer sb = new StringBuffer();
        sb.append(w84PaddingStr);
        for (int i = 0; i < digit - w84PaddingStr.length(); i++) {
            sb.append(paddingStr);
        }
        return sb.toString();
    }

    /**
     * 月份补全 2位
     * @param month 月份
     */
    public static String monthPadding(int month){
        return leftPadding(Integer.valueOf(month).toString(),2,"0");
    }

    /**
     * 格式化字符串
     * @param template 模版字符 占位使用%s
     * @param args 参数
     */
    public static String format(String template, Object... args) {
        template = String.valueOf(template);
        StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
        int templateStart = 0;

        int i;
        int placeholderStart;
        for(i = 0; i < args.length; templateStart = placeholderStart + 2) {
            placeholderStart = template.indexOf("%s", templateStart);
            if(placeholderStart == -1) {
                break;
            }

            builder.append(template.substring(templateStart, placeholderStart));
            builder.append(args[i++]);
        }

        builder.append(template.substring(templateStart));
        if(i < args.length) {
            builder.append(" [");
            builder.append(args[i++]);

            while(i < args.length) {
                builder.append(", ");
                builder.append(args[i++]);
            }

            builder.append(']');
        }

        return builder.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串
     *
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 是否包含数字
     * @param content 字符串
     * @return 是否包含
     */
    public static boolean isContainNumber(String content){
        if(isNotEmpty(content)){
            Matcher m = NUMBER_REGEX_PATTERN.matcher(content);
            return m.matches();
        }
        return false;
    }

    /**
     * 是否不以数字结尾
     * @param content 字符串
     * @return boolean
     */
    public static boolean isNotEndOfNumber(String content){
        if(isNotEmpty(content)){
            return !isContainNumber(Character.toString(content.charAt(content.length() - 1)));
        }
        return false;
    }

    /**
     * 字符串是否为纯数字
     * @param content 字符串
     * @return 是否为纯数字
     */
    public static boolean isNumeric(String content) {
        if(isNotEmpty(content)){
            Matcher isNum = PURE_NUMBER_REGEX_PATTERN.matcher(content);
            return isNum.matches();
        }
        return false;
    }

    public static boolean isStringHaveChange(String a,String b){
        if(isEmpty(a)){
            return isNotEmpty(b) ;
        }
        if(isNotEmpty(b)){
            return !null2EmptyWithTrimNew(a).equals(null2EmptyWithTrimNew(b));
        }else{
            return false;
        }
    }
}
