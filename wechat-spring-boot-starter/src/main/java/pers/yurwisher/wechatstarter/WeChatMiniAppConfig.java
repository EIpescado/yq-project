package pers.yurwisher.wechatstarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yq
 * @date 2018/12/06 14:54
 * @description 微信公众号配置
 * @since V1.0.0
 */
@ConfigurationProperties(prefix = "yurwisher.wechat.mini-app")
@Data
public class WeChatMiniAppConfig {

    /**appId*/
    private String appId ;
    /**密钥*/
    private String secret;
    /**
     * enable mini-app
     */
    private boolean enable = true;
}
