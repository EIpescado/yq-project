package pers.yurwisher.wechatstarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import pers.yurwisher.wechat.core.bean.WeChatPushConfig;

/**
 * @author yq
 * @date 2018/12/06 14:54
 * @description 微信公众号配置
 * @since V1.0.0
 */
@ConfigurationProperties(prefix = "yurwisher.wechat.mp")
@Data
public class WeChatMpConfig {

    private WeChatPushConfig config;
    /**
     * enabled wechat mp config
     */
    private boolean enable = true;
}
