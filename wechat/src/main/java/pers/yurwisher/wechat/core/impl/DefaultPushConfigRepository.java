package pers.yurwisher.wechat.core.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.wechat.common.base.WxAccessToken;
import pers.yurwisher.wechat.core.PushConfigRepository;
import pers.yurwisher.wechat.core.CoreService;
import pers.yurwisher.wechat.core.bean.WeChatPushConfig;

/**
 * @author yq
 * @date 2020/07/16 12:13
 * @description 默认的带消息推送的微信配置
 * @since V1.0.0
 */
public class DefaultPushConfigRepository implements PushConfigRepository {
    private static final Logger logger = LoggerFactory.getLogger(DefaultPushConfigRepository.class);

    private CoreService coreService;

    public DefaultPushConfigRepository(WeChatPushConfig weChatPushConfig) {
      this.appId = weChatPushConfig.getAppId();
      this.secret = weChatPushConfig.getSecret();
      this.token = weChatPushConfig.getToken();
      this.aesKey = weChatPushConfig.getAesKey();
    }

    public void setCoreService(CoreService coreService) {
        this.coreService = coreService;
    }
    /**
     * 公众号对应appId 开发者ID
     */
    protected volatile String appId;
    /**
     * 开发者密码(AppSecret)
     */
    protected volatile String secret;
    /**
     * 服务器配置令牌 token
     */
    protected volatile String token;
    /**
     * 服务器配置 消息加解密密钥(EncodingAESKey)
     */
    protected volatile String aesKey;
    /**
     * 微信接口请求token
     */
    protected volatile String accessToken;
    /**
     * 微信接口请求token 有效时间
     */
    protected volatile long expiresTime = -1L;

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String getAesKey() {
        return aesKey;
    }

    @Override
    public String getAccessToken() {
        //token已作废
        if(expiresTime == -1L || expiresTime - System.currentTimeMillis() <= 0){
            if(isAutoRefresh()){
                WxAccessToken newToken = coreService.getAccessToken();
                updateAccessToken(newToken);
            }
        }
        return accessToken ;
    }

    @Override
    public long getExpiresTime() {
        return expiresTime;
    }

    @Override
    public boolean isAutoRefresh() {
        return true;
    }

    @Override
    public void updateAccessToken(WxAccessToken newToken) {
        long now = System.currentTimeMillis();
        logger.info("刷新token,旧token有效期还剩余:{}",now - expiresTime );
        this.accessToken = newToken.getAccessToken() ;
        this.expiresTime = now + (newToken.getExpiresIn() - 200) * 1000L;
    }

    @Override
    public void expireAccessToken() {
        this.expiresTime = -1L;
    }

}
