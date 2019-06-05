package pers.yurwisher.wisp.utils;

import pers.yurwisher.wisp.enums.CustomTipEnum;
import pers.yurwisher.wisp.exception.CustomException;
import pers.yurwisher.wisp.wrapper.CustomTip;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * @author yq
 * @date 2019/01/29 09:47
 * @description 断言,错误码110
 * @since V1.0.0
 */
public class Asserts {

    private Asserts(){

    }

    /**
     * 必须为真
     * @param expression 表达式
     * @param message 具体的事物名称
     */
    public static void isTrue(boolean expression,String message) {
        if (!expression) {
            throw new CustomException(generateError(CustomTipEnum.MUST_TRUE,message));
        }
    }

    /**
     * 必须为真
     * @param expression 表达式
     */
    public static void isTrue(boolean expression) {
        isTrue(expression,null);
    }

    /**
     * 必须为假
     * @param expression 表达式
     * @param message 具体事物名称
     */
    public static void isFalse(boolean expression,String message) {
        if (expression) {
            throw new CustomException(generateError(CustomTipEnum.MUST_FALSE,message));
        }
    }

    /**
     * 必须为假
     * @param expression 表达式
     */
    public static void isFalse(boolean expression) {
         isFalse(expression,null);
    }

    /**
     *  必须为空
     * @param object 对象
     * @param message 具体事物名称
     */
    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new CustomException(generateError(CustomTipEnum.MUST_NULL,message));
        }
    }

    /**
     *  必须为空
     * @param object 对象
     */
    public static void isNull(Object object) {
        isNull(object,"object ");
    }


    /**
     * 不能为空
     * @param object 对象
     * @param message 具体事物名称
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new CustomException(generateError(CustomTipEnum.MUST_NOT_NULL,message));
        }
    }

    /**
     * 不能为空
     * @param object 对象
     */
    public static void notNull(Object object) {
        notNull(object,"object ");
    }

    /**
     * 必须为空字符
     * @param text 字符
     * @param message 具体
     */
    public static void isEmpty(CharSequence text, String message) {
        if (StringUtils.isNotEmpty(text)) {
            throw new CustomException(generateError(CustomTipEnum.MUST_EMPTY,message));
        }
    }

    public static void isEmpty(CharSequence text) {
        isEmpty(text, "CharSequence ");
    }

    /**
     * 必须不为空
     * @param text 字符
     * @param message 具体
     */
    public static void notEmpty(CharSequence text, String message) {
        if (StringUtils.isEmpty(text)) {
            throw new CustomException(generateError(CustomTipEnum.MUST_NOT_EMPTY,message));
        }
    }

    public static void notEmpty(CharSequence text) {
        notEmpty(text, "CharSequence ");
    }

    /**
     * 数组必须为空
     * @param array 数组
     * @param message 具体
     */
    public static void isEmpty(Object[] array, String message) {
        if (array != null && array.length > 0) {
            throw new CustomException(generateError(CustomTipEnum.MUST_EMPTY,message));
        }
    }

    public static void isEmpty(Object[] array) {
        isEmpty(array, "array ");
    }

    /**
     * 数组必须不为空
     * @param array 数组
     * @param message 具体
     */
    public static void notEmpty(Object[] array, String message) {
        if (array == null || array.length == 0) {
            throw new CustomException(generateError(CustomTipEnum.MUST_NOT_EMPTY,message));
        }
    }

    public static void notEmpty(Object[] array) {
        notEmpty(array, "array ");
    }


    /**
     * 必须为空
     * @param collection 集合
     * @param message 具体
     */
    public static void isEmpty(Collection collection, String message) {
        if (CollectionUtils.isNotEmpty(collection)) {
            throw new CustomException(generateError(CustomTipEnum.MUST_EMPTY,message));
        }
    }

    public static void isEmpty(Collection collection) {
        isEmpty(collection, "collection ");
    }

    /**
     * 必须不为空
     * @param collection 集合
     * @param message 具体
     */
    public static void notEmpty(Collection collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new CustomException(generateError(CustomTipEnum.MUST_NOT_EMPTY,message));
        }
    }

    public static void notEmpty(Collection collection) {
        notEmpty(collection, "collection ");
    }


    /**
     * 必须为空
     * @param map map
     * @param message 具体
     */
    public static void isEmpty(Map map, String message) {
        if (CollectionUtils.isNotEmpty(map)) {
            throw new CustomException(generateError(CustomTipEnum.MUST_EMPTY,message));
        }
    }

    public static void isEmpty(Map map) {
        isEmpty(map, "map ");
    }

    /**
     * 必须不为空
     * @param map map
     * @param message 具体
     */
    public static void notEmpty(Map map, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new CustomException(generateError(CustomTipEnum.MUST_NOT_EMPTY,message));
        }
    }

    public static void notEmpty(Map map) {
        notEmpty(map, "map ");
    }

    /**
     * 断言字符串为<code>null</code>、空字符串或者只包含空白字符串（空格、回车、换行、tab等字符）
     * @param text 字符串
     * @param message 具体
     */
    public static void isBlank(CharSequence text, String message) {
        if (StringUtils.isNotBlank(text)) {
            throw  new CustomException(generateError(CustomTipEnum.MUST_BLANK,message));
        }
    }

    public static void isBlank(CharSequence text) {
        isBlank(text, "charSequence ");
    }

    /**
     * 断言字符串不为<code>null</code>、空字符串或者只包含空白字符串（空格、回车、换行、tab等字符）
     * @param text 字符串
     * @param message 具体
     */
    public static void notBlank(CharSequence text, String message) {
        if (StringUtils.isBlank(text)) {
            throw  new CustomException(generateError(CustomTipEnum.MUST_NOT_BLANK,message));
        }
    }

    public static void notBlank(CharSequence text) {
        notBlank(text, "charSequence ");
    }

    /**
     * 包含指定字符串
     * @param textToSearch 字符串
     * @param substring 指定字符串
     */
    public static void containsText(String textToSearch, String substring) {
        if (StringUtils.isNotEmpty(textToSearch)
                && StringUtils.isNotEmpty(substring)
                && !textToSearch.contains(substring)) {
            throw  new CustomException(generateErrorSuffix(CustomTipEnum.MUST_CONTAIN,substring));
        }
    }

    /**
     * 不包含指定字符串
     * @param textToSearch 字符串
     * @param substring 指定字符串
     */
    public static void notContainsText(String textToSearch, String substring) {
        if (StringUtils.isNotEmpty(textToSearch)
                && StringUtils.isNotEmpty(substring)
                && textToSearch.contains(substring)) {
            throw  new CustomException(generateErrorSuffix(CustomTipEnum.MUST_NOT_CONTAIN,substring));
        }
    }

    /**
     * 以指定字符串开始
     * @param textToSearch
     * @param substring
     */
    public static void startsWithText(String textToSearch, String substring) {
        if (!StringUtils.isEmpty(textToSearch)
                && !StringUtils.isEmpty(substring)
                && !textToSearch.startsWith(substring)) {
            throw  new CustomException(generateErrorSuffix(CustomTipEnum.MUST_START_WITH,substring));
        }
    }

    /**
     * 不以指定字符串开始
     * @param textToSearch
     * @param substring
     */
    public static void notStartsWithText(String textToSearch, String substring) {
        if (!StringUtils.isEmpty(textToSearch)
                && !StringUtils.isEmpty(substring)
                && textToSearch.startsWith(substring)) {
            throw  new CustomException(generateErrorSuffix(CustomTipEnum.MUST_NOT_START_WITH,substring));
        }
    }

    /**
     * 以指定字符串结尾
     * @param textToSearch
     * @param substring
     */
    public static void endsWithText(String textToSearch, String substring) {
        if (!StringUtils.isEmpty(textToSearch)
                && !StringUtils.isEmpty(substring)
                && !textToSearch.endsWith(substring)) {
            throw  new CustomException(generateErrorSuffix(CustomTipEnum.MUST_END_WITH,substring));
        }
    }

    /**
     * 不以指定字符串结尾
     * @param textToSearch
     * @param substring
     */
    public static void notEndsWithText(String textToSearch, String substring) {
        if (!StringUtils.isEmpty(textToSearch)
                && !StringUtils.isEmpty(substring)
                && textToSearch.endsWith(substring)) {
            throw  new CustomException(generateErrorSuffix(CustomTipEnum.MUST_NOT_END_WITH,substring));
        }
    }

    /**
     * 不包含NULL
     * @param array
     */
    public static void noNullElements(Object[] array) {
        if (array == null) {
            return;
        }
        for (Object each : array) {
            if(each == null){
                throw  new CustomException(generateErrorSuffix(CustomTipEnum.MUST_NOT_CONTAIN_NULL,"array "));
            }
        }
    }

    public static void noNullElements(Collection collection) {
        if (collection == null) {
            return;
        }
        for (Object each : collection) {
            if(each == null){
                throw  new CustomException(generateErrorSuffix(CustomTipEnum.MUST_NOT_CONTAIN_NULL,"collection "));
            }
        }
    }

    public static void noNullElements(Map map) {
        if (map == null) {
            return;
        }
        for (Object each : map.values()) {
            if(each == null){
                throw  new CustomException(generateErrorSuffix(CustomTipEnum.MUST_NOT_CONTAIN_NULL,"map "));
            }
        }
    }

    private static CustomTip generateError(CustomTipEnum errorEnum, String message){
        String x = StringUtils.isNotEmpty(message) ? message  + errorEnum.getMsg() : errorEnum.getMsg() ;
        return new CustomTip(errorEnum.getCode(),x);
    }

    private static CustomTip generateErrorSuffix(CustomTipEnum errorEnum, String suffix){
        String x =  StringUtils.isNotEmpty(suffix) ? errorEnum.getMsg() + suffix : errorEnum.getMsg() ;
        return new CustomTip(errorEnum.getCode(),x);
    }

    /**
     * 大于0
     * @param bigDecimal 数字
     * @param message 消息
     */
    public static void greaterThenZero(BigDecimal bigDecimal,String message) {
        if (bigDecimal != null && !NumberUtils.greaterThenZero(bigDecimal)) {
            throw new CustomException(generateError(CustomTipEnum.MUST_GREATER_THEN_0,message));
        }
    }

    /**
     * 大于等于0
     * @param bigDecimal 数字
     * @param message 消息
     */
    public static void greaterThenOrEqualsZero(BigDecimal bigDecimal,String message) {
        if (bigDecimal != null && !NumberUtils.greaterOrEqualZero(bigDecimal)) {
            throw new CustomException(generateError(CustomTipEnum.MUST_GREATER_OR_EQUALS_0,message));
        }
    }

    /**
     * 小于等于0
     * @param bigDecimal 数字
     * @param message 消息
     */
    public static void lessOrEqualZero(BigDecimal bigDecimal,String message) {
        if (bigDecimal != null && !NumberUtils.lessOrEqualZero(bigDecimal)) {
            throw new CustomException(generateError(CustomTipEnum.MUST_LESS_OR_EQUALS_0,message));
        }
    }

    /**
     * 小于0
     * @param bigDecimal 数字
     * @param message 消息
     */
    public static void lessThenZero(BigDecimal bigDecimal,String message) {
        if (bigDecimal != null && !NumberUtils.lessThenZero(bigDecimal)) {
            throw new CustomException(generateError(CustomTipEnum.MUST_LESS_THEN_0,message));
        }
    }
}
