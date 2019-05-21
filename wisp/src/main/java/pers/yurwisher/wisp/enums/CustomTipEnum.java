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
    IS_NULL(new CustomTip(201," is null")),
    IS_NOT_TRUE(new CustomTip(202," is false")),
    IS_EMPTY(new CustomTip(203," is empty")),
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
