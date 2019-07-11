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
import pers.yurwisher.wechat.core.WeChatMpServlet;
import pers.yurwisher.wechat.mp.api.MpService;
import pers.yurwisher.wechat.mp.api.WxMessageRouter;
import pers.yurwisher.wechat.mp.api.impl.DefaultMpConfigRepository;
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
@ConditionalOnBean(WxMessageRouter.class)
public class WeChatMpAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(WeChatMpAutoConfiguration.class);

    private WeChatMpConfig weChatConfig;
    private WxMessageRouter wxMessageRouter;

    public WeChatMpAutoConfiguration(WeChatMpConfig weChatConfig, WxMessageRouter wxMessageRouter) {
        this.weChatConfig = weChatConfig;
        this.wxMessageRouter = wxMessageRouter;
    }

    @Bean
    //表示当classPath下存在HelloService.class文件时改配置文件类才有效
    @ConditionalOnClass(value = MpService.class)
    @ConditionalOnMissingBean(MpService.class)
    public MpService mpService(){
        DefaultMpConfigRepository repository = new DefaultMpConfigRepository();
        repository.setAppId(weChatConfig.getAppId());
        repository.setSecret(weChatConfig.getSecret());
        repository.setToken(weChatConfig.getServerToken());
        repository.setAesKey(weChatConfig.getAesKey());
        MpService mpService = new MpServiceImpl(repository);
        repository.setMpService(mpService);
        return mpService;
    }

    @Bean
    @ConditionalOnClass(value = {WeChatMpServlet.class,WxMessageRouter.class,MpService.class})
    @ConditionalOnMissingBean(name = "weChatMpServlet")
    public ServletRegistrationBean weChatMpServlet(MpService mpService){
        logger.info("init weChatMpServlet");
        return new ServletRegistrationBean(new WeChatMpServlet(wxMessageRouter,mpService),weChatConfig.getServerUrl());
    }

}
