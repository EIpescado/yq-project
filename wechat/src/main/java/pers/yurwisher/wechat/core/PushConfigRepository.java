package pers.yurwisher.wechat.core;

import pers.yurwisher.wechat.common.base.WxAccessToken;

/**
 * @author yq
 * @date 2018/12/18 15:01
 * @description 带消息推送的微信配置
 * @since V1.0.0
 */
public interface PushConfigRepository {

    /**
     * appId
     * @return appId
     */
    String getAppId();

    /**
     * secret
     * @return secret
     */
    String getSecret();

    /**服务器配置令牌 token*/
    String getToken();

    /**服务器配置 消息加解密密钥(EncodingAESKey)*/
    String getAesKey();

    /**微信接口请求token*/
    String getAccessToken();

    /**微信接口请求token 有效时间*/
    long getExpiresTime();

    /**是否自动刷新token*/
    boolean isAutoRefresh();

    /**
     * 更新token
     */
    void updateAccessToken(WxAccessToken newToken);

    /**
     * 作废token
     */
    void expireAccessToken();
}
