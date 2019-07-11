package pers.yurwisher.tokenstarter;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import pers.yurwisher.token.Token;

/**
 * @author yq
 * @date 2018/12/06 14:54
 * @description token配置
 * @since V1.0.0
 */
@Data
@ConfigurationProperties(prefix = "yurwisher.token")
public class TokenConfig {

    /**
     * token密钥
     */
    private String secret;
    /**
     * token过期时间,默认一天
     */
    private Long expireTime = 24 * 60 * 60 * 1000L;
    /**
     * token签发者
     */
    private String issuer ;
    /**
     * token接收者
     */
    private String audience = "user";
    /**
     * header签名算法,默认HS256
     */
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    /**
     * 客户自定义token
     */
    private Class<? extends Token> customTokenClass;
    /**
     * 是否启用Token
     */
    private boolean enable = true;
}
