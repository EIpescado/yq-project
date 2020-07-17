package pers.yurwisher.wechat.core.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yq
 * @date 2018/12/17 14:09
 * @description 登录凭证校验后的结果
 * @since V1.0.0
 */
@Data
public class JsCode2Session implements Serializable {
    private static final long serialVersionUID = 767144719592602547L;

    private String sessionKey;

    private String openid;

    private String unionid;
}
