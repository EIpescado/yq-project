package pers.yurwisher.wisp.enums;

import pers.yurwisher.wisp.wrapper.CustomTip;

/**
 * @author yq
 * @date 2019/05/21 11:28
 * @description 提示枚举
 * @since V1.0.0
 */
public enum CustomTipEnum implements ICustomTipEnum {
    ERROR(new CustomTip(1,"error")),
    MUST_NULL(new CustomTip(201,"must be null")),
    MUST_NOT_NULL(new CustomTip(201,"must not be null")),
    MUST_TRUE(new CustomTip(201,"must be true")),
    MUST_FALSE(new CustomTip(202,"must be false")),
    MUST_EMPTY(new CustomTip(203,"must be empty")),
    MUST_NOT_EMPTY(new CustomTip(203,"must not be empty")),
    MUST_BLANK(new CustomTip(203,"must be null or blank")),
    MUST_NOT_BLANK(new CustomTip(203,"must not be null or blank")),
    MUST_CONTAIN(new CustomTip(203," must contain the substring ")),
    MUST_NOT_CONTAIN(new CustomTip(203," must not contain the substring ")),
    MUST_START_WITH(new CustomTip(203," must start with the substring ")),
    MUST_NOT_START_WITH(new CustomTip(203," must not start with the substring ")),
    MUST_END_WITH(new CustomTip(203," must end with the substring ")),
    MUST_NOT_END_WITH(new CustomTip(203," must not end with the substring ")),
    MUST_NOT_CONTAIN_NULL(new CustomTip(203," must not contain any null elements")),
    NOT_FOUND(new CustomTip(404,"404,not found")),
    METHOD_NOT_ALLOWED(new CustomTip(405,"405,method not allowed")),
    UNSUPPORTED_MEDIA_TYPE(new CustomTip(415,"415,Unsupported Media Type")),
    SERVER_ERROR(new CustomTip(500,"500,server error")),
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
