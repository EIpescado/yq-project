package pers.yurwisher.wisp.utils;

import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yq on 2017/01/04 17:15.
 */
public class StringUtils {

    /**
     * 匹配所有特殊字符的正则表达式
     */
    private static final String SPECIAL_CHARACTER_REGEX = "[`~!@#$%^&*()\\-+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）—_+|{}【】‘；：”“’。，、\"？\\s]";

    private static final String SPECIAL_CHARACTER_REGEX_2 = "[`~!@#$%^&*()\\-+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）—_+|{}【】‘；：”“’。，、\"？\\s]";

    private static final Pattern SPECIAL_CHARACTER_PATTERN = Pattern.compile(SPECIAL_CHARACTER_REGEX);

    private static final Pattern SPECIAL_CHARACTER_PATTERN_2 = Pattern.compile(SPECIAL_CHARACTER_REGEX_2);

    /**
     * 中文正则
     */
    private static final String CHINESE_REGEX = "[\u4e00-\u9fa5]";

    private static final Pattern CHINESE_REGEX_PATTERN = Pattern.compile(CHINESE_REGEX);

    /**
     * 数字正则
     */
    private static final String NUMBER_REGEX = ".*\\d+.*";

    private static final Pattern NUMBER_REGEX_PATTERN = Pattern.compile(NUMBER_REGEX);

    /**
     * 匹配括号中间内容
     */
    private static final Pattern BRACKET_CONTENT_PATTERN = Pattern.compile("(?<=\\()(.+?)(?=\\))");

    private static final char UNDERLINE = '_';


    /**
     * 纯数字正则
     */
    private static final String PURE_NUMBER_REGEX = "[0-9]*";

    private static final Pattern PURE_NUMBER_REGEX_PATTERN = Pattern.compile(PURE_NUMBER_REGEX);

    private static final String VERIFICATION_CODE_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final String EMPTY_JSON = "{}";
    private static final char BACKSLASH = '\\';
    private static final char DELIM_START = '{';

    private StringUtils() {
    }

    /**
     * 二进制转字符串
     */
    static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 字符串去左右空格
     */
    public static String null2EmptyWithTrim(String s) {
        if (s == null) {
            return "";
        } else {
            return s.trim();
        }
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

    /**
     * 字符串是否为空
     */
    public static boolean isEmpty(String foo) {
        return (foo == null || foo.trim().length() == 0);
    }

    /**
     * 字符串是否为空
     */
    public static boolean isEmpty(CharSequence foo) {
        return foo == null || foo.length() == 0;
    }

    /**
     * 字符串是否不为空
     */
    public static boolean isNotEmpty(String foo) {
        return (null != foo && foo.trim().length() > 0);
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 计算一个字符串的长度，汉字当成两个字符计算，ascii英文字符当成一个。
     *
     * @param aStr 要计算长度的目标字符串
     * @return 计算出的长度
     */
    public static int lengthOfCN(String aStr) {
        char c;
        int length = 0;
        if (isNotEmpty(aStr)) {
            for (int i = 0; i < aStr.length(); i++) {
                c = aStr.charAt(i);
                if (c >= 127) {
                    length += 2;
                } else {
                    length += 1;
                }
            }
        }
        return length;
    }

    /**
     * 字符串首字母大写或小写
     */
    public static String firstLetterUpperOrLower(String str, boolean isLowerCase) {
        if (isNotEmpty(str)) {
            if (str.length() == 1) {
                return isLowerCase ? str.toLowerCase() : str.toUpperCase();
            } else {
                String first = str.substring(0, 1).toLowerCase();
                first = isLowerCase ? first.toLowerCase() : first.toUpperCase();
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
     * 去除字符串所有特殊字符,除开小数点
     */
    public static String removeSpecialChar(String str) {
        Matcher m = SPECIAL_CHARACTER_PATTERN_2.matcher(str);
        return m.replaceAll("").trim();
    }


    /**
     * 判断str是否在strArr中
     */
    public static boolean strInArray(String str, String[] strArr) {
        for (String s : strArr) {
            if (str.equals(s)) {
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
        while (st.hasMoreTokens()) {
            //返回从当前位置到下一个分隔符的字符串
            values[pos++] = st.nextToken();
        }
        return values;
    }

    /**
     * 判断字符串中是否包含中文
     */
    public static boolean isContainChinese(String str) {
        if (isNotEmpty(str)) {
            Matcher m = CHINESE_REGEX_PATTERN.matcher(str);
            return m.find();
        }
        return false;
    }

    /**
     * 获取字符串中所有中文
     */
    public static String getAllChineseInStr(String str) {
        return str.replaceAll("[^\u4e00-\u9fa5]", "");
    }

    /**
     * 获取括号内内容
     *
     * @param str
     */
    public static String getBracketContent(String str) {
        Matcher matcher = BRACKET_CONTENT_PATTERN.matcher(str);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return "";
        }
    }

    /**
     * 左补全字符
     *
     * @param w84PaddingStr 需要补全的字符
     * @param digit         补全后字符的位数
     * @param paddingStr    补全使用的字符
     */
    public static String leftPadding(String w84PaddingStr, int digit, String paddingStr) {
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
     *
     * @param w84PaddingStr 需要补全的字符
     * @param digit         补全后字符的位数
     * @param paddingStr    补全使用的字符
     */
    public static String rightPadding(String w84PaddingStr, int digit, String paddingStr) {
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
     *
     * @param month 月份
     */
    public static String monthPadding(int month) {
        return leftPadding(Integer.valueOf(month).toString(), 2, "0");
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
     *
     * @param content 字符串
     * @return 是否包含
     */
    public static boolean isContainNumber(String content) {
        if (isNotEmpty(content)) {
            Matcher m = NUMBER_REGEX_PATTERN.matcher(content);
            return m.matches();
        }
        return false;
    }

    /**
     * 是否不以数字结尾
     *
     * @param content 字符串
     * @return boolean
     */
    public static boolean isNotEndOfNumber(String content) {
        if (isNotEmpty(content)) {
            return !isContainNumber(Character.toString(content.charAt(content.length() - 1)));
        }
        return false;
    }

    /**
     * 字符串是否为纯数字
     *
     * @param content 字符串
     * @return 是否为纯数字
     */
    public static boolean isNumeric(String content) {
        if (isNotEmpty(content)) {
            Matcher isNum = PURE_NUMBER_REGEX_PATTERN.matcher(content);
            return isNum.matches();
        }
        return false;
    }

    public static boolean isStringHaveChange(String a, String b) {
        if (isEmpty(a)) {
            return isNotEmpty(b);
        }
        if (isNotEmpty(b)) {
            return !null2EmptyWithTrimNew(a).equals(null2EmptyWithTrimNew(b));
        } else {
            return false;
        }
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static String random(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(VERIFICATION_CODE_CHARS.charAt(ThreadLocalRandom.current().nextInt(62)));
        }
        return sb.toString();
    }

    /**
     * 格式化字符串<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") =》 this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") =》 this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") =》 this is \a for b<br>
     *
     * @param strPattern 字符串模板
     * @param argArray   参数列表
     * @return 结果
     */
    public static String format2(final String strPattern, final Object... argArray) {
        if (isEmpty(strPattern) || argArray == null || !(argArray.length > 0)) {
            return strPattern;
        }
        final int strPatternLength = strPattern.length();

        // 初始化定义好的长度以获得更好的性能
        StringBuilder sbuf = new StringBuilder(strPatternLength + 50);
        // 记录已经处理到的位置
        int handledPosition = 0;
        // 占位符所在位置
        int delimIndex;
        for (int argIndex = 0; argIndex < argArray.length; argIndex++) {
            delimIndex = strPattern.indexOf(EMPTY_JSON, handledPosition);
            // 剩余部分无占位符
            if (delimIndex == -1) {
                // 不带占位符的模板直接返回
                if (handledPosition == 0) {
                    return strPattern;
                }
                // 字符串模板剩余部分不再包含占位符，加入剩余部分后返回结果
                sbuf.append(strPattern, handledPosition, strPatternLength);
                return sbuf.toString();
            }
            // 转义符
            if (delimIndex > 0 && strPattern.charAt(delimIndex - 1) == BACKSLASH) {
                // 双转义符
                if (delimIndex > 1 && strPattern.charAt(delimIndex - 2) == BACKSLASH) {
                    // 转义符之前还有一个转义符，占位符依旧有效
                    sbuf.append(strPattern, handledPosition, delimIndex - 1);
                    sbuf.append(argArray[argIndex]);
                    handledPosition = delimIndex + 2;
                } else {
                    // 占位符被转义
                    argIndex--;
                    sbuf.append(strPattern, handledPosition, delimIndex - 1);
                    sbuf.append(DELIM_START);
                    handledPosition = delimIndex + 1;
                }
            } else {// 正常占位符
                sbuf.append(strPattern, handledPosition, delimIndex);
                sbuf.append(argArray[argIndex]);
                handledPosition = delimIndex + 2;
            }
        }

        // append the characters following the last {} pair.
        // 加入最后一个占位符后所有的字符
        sbuf.append(strPattern, handledPosition, strPattern.length());

        return sbuf.toString();
    }

}
