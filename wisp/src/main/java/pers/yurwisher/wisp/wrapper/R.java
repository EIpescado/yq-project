package pers.yurwisher.wisp.wrapper;

import pers.yurwisher.wisp.enums.ICustomTipEnum;

import java.io.Serializable;

/**
 * 返回结果
 * @author yq 2018年12月27日 16:03:56
 */
public class R<T> implements Serializable {

    private static final String SUCCESS_CODE = "0";
    private static final String FAILURE_CODE = "1";
    private static final String SUCCESS_MESSAGE = "success";
    private static final long serialVersionUID = -986092880650589025L;

    /**返回结果 编码 1：成功 0：失败*/
    private String code ;
    /**返回结果 描述信息*/
    private String message ;
    /**返回结果*/
    private T res  ;

    public R() {
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MESSAGE;
    }

    public R(String code, String message, T res) {
        this.code = code;
        this.message = message;
        this.res = res;
    }

    /**判断返回是否成功*/
    public boolean succeed(){
        return code.equals(SUCCESS_CODE);
    }

    /**返回成功*/
    public static <T> R<T> ok(T res){
        return new R<>(SUCCESS_CODE,SUCCESS_MESSAGE,res);
    }

    public static R ok(){
        return new R<>(SUCCESS_CODE,SUCCESS_MESSAGE,null);
    }

    /**返回失败*/
    public static R fail(String message){
        return new R<>(FAILURE_CODE,message,null);
    }

    /**返回失败*/
    public static R fail(CustomTip error){
        return new R<>(error.getCode(),error.getMsg(),null);
    }

    /**返回失败*/
    public static R fail(ICustomTipEnum error){
        return fail(error.tip());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getRes() {
        return res;
    }

    public void setRes(T res) {
        this.res = res;
    }

}
