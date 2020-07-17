package pers.yurwisher.wechatstarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import pers.yurwisher.wechat.core.bean.WeChatPushConfig;

/**
 * @author yq
 * @date 2018/12/06 14:54
 * @description 微信小程序配置
 * @since V1.0.0
 */
@ConfigurationProperties(prefix = "yurwisher.wechat.mini-app")
@Data
public class WeChatMiniAppConfig {


    private WeChatPushConfig config;

    /**
     * enable mini-app
     */
    private boolean enable = true;
}
