package pers.yurwisher.wechatstarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.yurwisher.wechat.open.api.CoreService;
import pers.yurwisher.wechat.open.api.impl.CoreServiceImpl;
import pers.yurwisher.wechat.open.api.impl.DefaultMiniAppConfigRepository;

/**
 * @author yq
 * @date 2018/12/06 14:56
 * @description weChat auto config
 * @since V1.0.0
 */
@Configuration
@EnableConfigurationProperties(value = WeChatMiniAppConfig.class)
@ConditionalOnProperty(prefix = "yurwisher.wechat.mini-app", value = "enable", matchIfMissing = true)
public class WeChatMiniAppAutoConfiguration {

    private WeChatMiniAppConfig weChatMiniAppConfig;

    public WeChatMiniAppAutoConfiguration(WeChatMiniAppConfig weChatMiniAppConfig) {
        this.weChatMiniAppConfig = weChatMiniAppConfig;
    }

    @Bean
    @ConditionalOnClass(value = CoreService.class)
    @ConditionalOnMissingBean(CoreService.class)
    public CoreService coreService(){
        DefaultMiniAppConfigRepository repository = new DefaultMiniAppConfigRepository();
        repository.setAppId(weChatMiniAppConfig.getAppId());
        repository.setSecret(weChatMiniAppConfig.getSecret());
        return new CoreServiceImpl(repository);
    }

}
