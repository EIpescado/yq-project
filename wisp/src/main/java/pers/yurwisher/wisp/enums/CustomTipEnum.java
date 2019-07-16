package pers.yurwisher.wisp.enums;

import pers.yurwisher.wisp.wrapper.CustomTip;

/**
 * @author yq
 * @date 2019/05/21 11:28
 * @description 提示枚举
 * @since V1.0.0
 */
public enum CustomTipEnum implements ICustomTipEnum {
    ERROR(CustomTip.of(1,"error")),
    MUST_NULL(CustomTip.of(201,"must be null")),
    MUST_NOT_NULL(CustomTip.of(202,"must not be null")),
    MUST_TRUE(CustomTip.of(203,"must be true")),
    MUST_FALSE(CustomTip.of(204,"must be false")),
    MUST_EMPTY(CustomTip.of(205,"must be empty")),
    MUST_NOT_EMPTY(CustomTip.of(206,"must not be empty")),
    MUST_BLANK(CustomTip.of(207,"must be null or blank")),
    MUST_NOT_BLANK(CustomTip.of(208,"must not be null or blank")),
    MUST_CONTAIN(CustomTip.of(209," must contain the substring ")),
    MUST_NOT_CONTAIN(CustomTip.of(210," must not contain the substring ")),
    MUST_START_WITH(CustomTip.of(211," must start with the substring ")),
    MUST_NOT_START_WITH(CustomTip.of(212," must not start with the substring ")),
    MUST_END_WITH(CustomTip.of(213," must end with the substring ")),
    MUST_NOT_END_WITH(CustomTip.of(214," must not end with the substring ")),
    MUST_NOT_CONTAIN_NULL(CustomTip.of(215," must not contain any null elements")),
    MUST_GREATER_THEN_0(CustomTip.of(216," must greater then 0")),
    MUST_LESS_THEN_0(CustomTip.of(217," must less then 0")),
    MUST_GREATER_OR_EQUALS_0(CustomTip.of(218," must greater or equals 0")),
    MUST_LESS_OR_EQUALS_0(CustomTip.of(219," must less or equals 0")),
    NOT_FOUND(CustomTip.of(404,"404,not found")),
    METHOD_NOT_ALLOWED(CustomTip.of(405,"405,method not allowed")),
    UNSUPPORTED_MEDIA_TYPE(CustomTip.of(415,"415,Unsupported Media Type")),
    SERVER_ERROR(CustomTip.of(500,"500,server error")),
    ;

    private CustomTip tip;

    CustomTipEnum(CustomTip tip) {
        this.tip = tip;
    }

    @Override
    public CustomTip tip() {
        return tip;
    }

}
