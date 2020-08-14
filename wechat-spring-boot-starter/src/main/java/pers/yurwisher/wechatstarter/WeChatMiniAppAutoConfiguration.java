package pers.yurwisher.wechatstarter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.yurwisher.wechat.core.CoreService;
import pers.yurwisher.wechat.core.PushConfigRepository;
import pers.yurwisher.wechat.core.impl.CoreServiceImpl;
import pers.yurwisher.wechat.core.impl.DefaultPushConfigRepository;
import pers.yurwisher.wechat.miniapp.MiniappService;
import pers.yurwisher.wechat.miniapp.WeChatMiniAppServlet;
import pers.yurwisher.wechat.miniapp.impl.MiniappServiceImpl;

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

    private static final Logger logger = LoggerFactory.getLogger(WeChatMiniAppAutoConfiguration.class);

    private WeChatMiniAppConfig weChatMiniAppConfig;

    public WeChatMiniAppAutoConfiguration(WeChatMiniAppConfig weChatMiniAppConfig) {
        this.weChatMiniAppConfig = weChatMiniAppConfig;
    }

    @Bean
    @ConditionalOnClass(value = CoreService.class)
    @ConditionalOnMissingBean(name = "weChatMiniAppCoreService")
    public CoreService weChatMiniAppCoreService(){
        DefaultPushConfigRepository repository = new DefaultPushConfigRepository(weChatMiniAppConfig.getConfig());
        CoreService coreService = new CoreServiceImpl(repository);
        repository.setCoreService(coreService);
        return coreService;
    }

    @Bean
    @ConditionalOnClass(value = CoreService.class)
    @ConditionalOnBean(name = "weChatMiniAppCoreService")
    @ConditionalOnMissingBean(name = "miniAppService")
    public MiniappService miniAppService(CoreService weChatMiniAppCoreService){
        return new MiniappServiceImpl(weChatMiniAppCoreService);
    }

    @Bean
    @ConditionalOnClass(value = {WeChatMiniAppServlet.class,CoreService.class})
    @ConditionalOnBean(name = "weChatMiniAppCoreService")
    @ConditionalOnMissingBean(name = "weChatMiniAppServlet")
    public ServletRegistrationBean weChatMiniAppServlet(CoreService weChatMiniAppCoreService){
        logger.info("init weChatMiniAppServlet");
        return new ServletRegistrationBean(new WeChatMiniAppServlet(weChatMiniAppCoreService),weChatMiniAppConfig.getConfig().getServerUrl());
    }
}
