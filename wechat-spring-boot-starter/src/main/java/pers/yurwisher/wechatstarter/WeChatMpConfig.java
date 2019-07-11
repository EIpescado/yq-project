package pers.yurwisher.wechatstarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yq
 * @date 2018/12/06 14:54
 * @description 微信公众号配置
 * @since V1.0.0
 */
@ConfigurationProperties(prefix = "yurwisher.wechat.mp")
@Data
public class WeChatMpConfig {

    /**公众号appId*/
    private String appId ;
    /**公众号密钥*/
    private String secret;
    /**服务器配置令牌 token*/
    private String serverToken;
    /**服务器配置 消息加解密密钥(EncodingAESKey)*/
    private String aesKey;
    /**
     * weChat 服务请求url
     */
    private String serverUrl = "/weChat";
    /**
     * enabled wechat mp config
     */
    private boolean enable = true;
}
