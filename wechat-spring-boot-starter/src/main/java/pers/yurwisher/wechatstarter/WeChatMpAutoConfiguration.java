package pers.yurwisher.wechatstarter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.yurwisher.wechat.core.CoreService;
import pers.yurwisher.wechat.core.impl.CoreServiceImpl;
import pers.yurwisher.wechat.core.impl.DefaultPushConfigRepository;
import pers.yurwisher.wechat.mp.WeChatMpServlet;
import pers.yurwisher.wechat.mp.api.MpService;
import pers.yurwisher.wechat.mp.api.WxMessageRouter;
import pers.yurwisher.wechat.mp.api.impl.MpServiceImpl;

/**
 * @author yq
 * @date 2018/12/06 14:56
 * @description weChat auto config
 * @since V1.0.0
 */
@Configuration
//启动配置文件，value用来指定我们要启用的配置类
@EnableConfigurationProperties(value = WeChatMpConfig.class)
//表示只有我们的配置文件是否配置了以yq.weChat为前缀的资源项值，并且在该资源项值为enable，如果没有配置我们默认设置为enable
@ConditionalOnProperty(prefix = "yurwisher.wechat.mp", value = "enable", matchIfMissing = true)
public class WeChatMpAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(WeChatMpAutoConfiguration.class);

    private WeChatMpConfig weChatConfig;

    public WeChatMpAutoConfiguration(WeChatMpConfig weChatConfig) {
        this.weChatConfig = weChatConfig;
    }

    @Bean
    @ConditionalOnClass(value = CoreService.class)
    @ConditionalOnMissingBean(name = "mpCoreService")
    public CoreService mpCoreService(){
        DefaultPushConfigRepository repository = new DefaultPushConfigRepository(weChatConfig.getConfig());
        CoreService coreService = new CoreServiceImpl(repository);
        repository.setCoreService(coreService);
        return coreService;
    }

    @Bean
    @ConditionalOnClass(value = MpService.class)
    @ConditionalOnMissingBean(name = "mpService")
    public MpService mpService(CoreService mpCoreService){
        return new MpServiceImpl(mpCoreService);
    }

    @Bean
    @ConditionalOnClass(value = WxMessageRouter.class)
    @ConditionalOnMissingBean(name = "wxMessageRouter")
    public WxMessageRouter wxMessageRouter(){
        return new WxMessageRouter();
    }

    @Bean
    @ConditionalOnClass(value = {WeChatMpServlet.class,WxMessageRouter.class,CoreService.class})
    @ConditionalOnBean(name = "wxMessageRouter")
    @ConditionalOnMissingBean(name = "weChatMpServlet")
    public ServletRegistrationBean weChatMpServlet(CoreService mpCoreService,WxMessageRouter wxMessageRouter){
        logger.info("init weChatMpServlet");
        return new ServletRegistrationBean(new WeChatMpServlet(wxMessageRouter,mpCoreService),weChatConfig.getConfig().getServerUrl());
    }

}
