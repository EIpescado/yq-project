package pers.yurwisher.wisp.exception;

import pers.yurwisher.wisp.enums.ICustomTipEnum;
import pers.yurwisher.wisp.wrapper.CustomTip;

/**
 * @author yq
 * @date 2019/05/21 11:34
 * @description 自定义异常
 * @since V1.0.0
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = -4083494081772087464L;

    private CustomTip customTip;

    public CustomTip tip() {
        return customTip;
    }

    public CustomException(CustomTip customTip) {
        this.customTip = customTip;
    }

    public CustomException(CustomTip customTip,Object...args) {
        this.customTip = CustomTip.of(customTip.getCode(),String.format(customTip.getMsg(),args));
    }

    public CustomException(ICustomTipEnum customTipEnum){
        this(customTipEnum.tip());
    }

    public CustomException(ICustomTipEnum customTipEnum,Object...args){
        this(customTipEnum.tip(),args);
    }

}
