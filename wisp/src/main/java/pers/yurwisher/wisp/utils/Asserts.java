package pers.yurwisher.wisp.utils;

import pers.yurwisher.wisp.enums.CustomTipEnum;
import pers.yurwisher.wisp.exception.CustomException;
import pers.yurwisher.wisp.wrapper.CustomTip;

import java.util.Collection;

/**
 * @author yq
 * @date 2019/01/29 09:47
 * @description 断言,错误码110
 * @since V1.0.0
 */
public class Asserts {

    /**
     * 必须为真
     * @param expression 表达式
     * @param message 错误
     */
    public static void isTrue(boolean expression,String message) {
        if (!expression) {
            throw new CustomException(generateError(CustomTipEnum.IS_NOT_TRUE,message));
        }
    }

    /**
     * 不能为空
     * @param object 对象
     * @param message 错误
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new CustomException(generateError(CustomTipEnum.IS_NULL,message));
        }
    }

    /**
     * 必须不空
     * @param collection 集合
     * @param message 错误
     */
    public static void notEmpty(Collection collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new CustomException(generateError(CustomTipEnum.IS_EMPTY,message));
        }
    }

    /**
     * 必须不空
     * @param str 字符串
     * @param message 错误
     */
    public static void notEmpty(String str, String message) {
        if (StringUtils.isEmpty(str)) {
            throw new CustomException(generateError(CustomTipEnum.IS_EMPTY,message));
        }
    }

    private static CustomTip generateError(CustomTipEnum errorEnum, String message){
        return new CustomTip(errorEnum.getCode(),message  + errorEnum.getMsg());
    }
}
