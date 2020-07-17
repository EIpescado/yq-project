package pers.yurwisher.wechat.miniapp.message;

import lombok.Data;

/**
 * @author yq
 * @date 2020/07/08 10:05
 * @description
 * @since V1.0.0
 */
@Data
public class SubscribeMessage {

    /**接收者openid*/
    private String touser ;

    /**模板ID*/
    private String template_id ;

    /**点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转*/
    private String page;

    /**模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } } */
    private Object data;

    /**跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版*/
    private String miniprogram_state;

    /**进入小程序查看”的语言类型，支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)，默认为zh_CN*/
    private String lang;
}
