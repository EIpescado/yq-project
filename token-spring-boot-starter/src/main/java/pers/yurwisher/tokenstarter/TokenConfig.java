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
     * token过期时间,默认一小时
     */
    private Long expireTime = 60 * 60 * 1000L;
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
