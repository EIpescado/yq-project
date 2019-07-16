package pers.yurwisher.tokenstarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.yurwisher.token.TokenHelper;

/**
 * @author yq
 * @date 2018/12/06 14:56
 * @description token自动配置
 * @since V1.0.0
 */
@Configuration
@EnableConfigurationProperties(value = TokenConfig.class)
@ConditionalOnProperty(prefix = "yurwisher.token", value = "enable", matchIfMissing = true)
public class TokenAutoConfiguration {

    private TokenConfig tokenConfig;

    public TokenAutoConfiguration(TokenConfig tokenConfig) {
        this.tokenConfig = tokenConfig;
    }

    /**
     * ConditionalOnClass : classpath下存在此类
     * ConditionalOnMissingBean :spring上下文不存在此bean
     */
    @Bean
    @ConditionalOnClass(value = TokenHelper.class)
    @ConditionalOnMissingBean(TokenHelper.class)
    public TokenHelper tokenHelper(){
        TokenHelper tokenHelper =  new TokenHelper(tokenConfig.getSecret(), tokenConfig.getExpireTime(), tokenConfig.getSignatureAlgorithm());
        tokenHelper.setCustomTokenClass(tokenConfig.getCustomTokenClass());
        return tokenHelper;
    }

}
