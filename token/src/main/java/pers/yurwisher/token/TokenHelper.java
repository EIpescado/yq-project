package pers.yurwisher.token;


import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.token.exception.TokenException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * token工具类
 * @author  yq
 */
@Getter
@Setter
public class TokenHelper {

    private static final Logger log = LoggerFactory.getLogger(TokenHelper.class);

    /**
     * token密钥
     */
    private String secret;
    /**
     * token过期时间
     */
    private Long expireTime;
    /**
     * 签名密钥
     */
    private SecretKey signingKey;
    /**
     * header签名算法
     */
    private SignatureAlgorithm signatureAlgorithm;

    /**
     * 自定义token类全路径名
     */
    private Class<? extends Token> customTokenClass;

    /**
     * 默认过期时间 两小时
     */
    private static final Long DEFAULT_EXPIRE_TIME = 2 * 60 * 60 * 1000L;

    /**
     * 默认可以自动刷新token的时间 一小时,即只要token未过期且当前时间 - 签发时间 >= 1小时,即刷新token
     */
    private static final Long DEFAULT_REFRESH_TIME = 60 * 60 * 1000L;

    /**
     * token自动刷新时间
     */
    private Long refreshTime;

    public TokenHelper(String tokenSecret, Long tokenExpireTime, Long refreshTime,SignatureAlgorithm signatureAlgorithm) {
        this.secret = assertIsEmpty(tokenSecret);
        this.expireTime = tokenExpireTime != null ? tokenExpireTime : DEFAULT_EXPIRE_TIME;
        this.refreshTime = refreshTime != null ? refreshTime : DEFAULT_REFRESH_TIME;
        this.signatureAlgorithm = signatureAlgorithm == null ? SignatureAlgorithm.HS256 : signatureAlgorithm;
        this.signingKey = generalKey();
    }

    public TokenHelper(String tokenSecret, Long tokenExpireTime,Long refreshTime) {
        this(tokenSecret,tokenExpireTime,refreshTime,null);
    }

    /**
     * 由字符串生成加密key
     * @return SecretKey
     */
    private SecretKey generalKey(){
        // 生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    private String assertIsEmpty(String x){
        if(x == null || x.length() == 0){
            throw new TokenException("check your config,secret and issuer must be set");
        }
        return x;
    }

}
