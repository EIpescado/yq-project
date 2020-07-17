package pers.yurwisher.wechat.core.bean;

/**
 * @author yq
 * @date 2020/07/16 14:25
 * @description 微信服务端配置
 * @since V1.0.0
 */
public class WeChatPushConfig {

    /**
     * 公众号对应appId 开发者ID
     */
    private String appId;
    /**
     * 开发者密码(AppSecret)
     */
    private String secret;
    /**
     * 服务器配置令牌 token
     */
    private String token;
    /**
     * 服务器配置 消息加解密密钥(EncodingAESKey)
     */
    private String aesKey;

    /**
     * weChat 服务请求url,即微信回调的接口地址
     */
    private String serverUrl = "/weChat";

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
