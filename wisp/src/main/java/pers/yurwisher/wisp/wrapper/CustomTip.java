package pers.yurwisher.wisp.wrapper;

import java.io.Serializable;

/**
 * @author yq
 * @date 2019/05/21 10:49
 * @description 自定义提示
 * @since V1.0.0
 */
public class CustomTip implements Comparable, Serializable {

    private static final long serialVersionUID = 2130725204329165149L;
    /**
     * 错误编码
     */
    private String code ;

    /**
     * 错误详情
     */
    private String msg;


    public CustomTip(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CustomTip(int code, String msg) {
        this.code = Integer.toString(code);
        this.msg = msg;
    }

    public static CustomTip of(String code, String msg){
        return new CustomTip(code,msg);
    }

    public static CustomTip of(int code, String msg){
        return new CustomTip(code,msg);
    }

    public CustomTip() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public int compareTo(Object o) {
        CustomTip tip = (CustomTip) o;
        return tip.getCode().compareTo(this.code);
    }
}
