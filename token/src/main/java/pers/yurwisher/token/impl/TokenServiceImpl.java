package pers.yurwisher.token.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import pers.yurwisher.token.ITokenService;
import pers.yurwisher.token.Token;
import pers.yurwisher.token.TokenHelper;
import pers.yurwisher.token.exception.TokenException;

/**
 * @author yq
 * @date 2018/12/10 15:21
 * @description tokenService
 * @since V1.0.0
 */
public class TokenServiceImpl<T extends Token> implements ITokenService<T> {

    private TokenHelper tokenHelper;

    public TokenServiceImpl(TokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    /**
     * 生成token
     */
    @Override
    public String generateToken(T token) {
        return generateToken(token, tokenHelper.getExpireTime());
    }

    /**
     * 解析token获取参数对象
     *
     * @param jwsToken token
     */
    @Override
    @SuppressWarnings("unchecked")
    public T parseToken(String jwsToken) {
        return (T) parseToken(jwsToken, tokenHelper.getCustomTokenClass());
    }

    @Override
    public Token parseToken(String jwsToken, Class<? extends Token> tokenClass) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(tokenHelper.getSigningKey())
                    .parseClaimsJws(jwsToken).getBody();
            return ((JSONObject) JSON.toJSON(claims)).toJavaObject(tokenClass);
        } catch (Exception e) {
            throw new TokenException("token parse fail: " + e.getLocalizedMessage());
        }
    }

    @Override
    public String generateToken(T token, Long expireTime) {
        long mills = System.currentTimeMillis();
        token.setDateCreated(mills);
        token.setExpiration(mills + expireTime);
        JwtBuilder builder = Jwts.builder()
                //自定义属性
                .setClaims(token.toJSON())
                //签名算法及密钥
                .signWith(tokenHelper.getSignatureAlgorithm(), tokenHelper.getSigningKey());
        return builder.compact();
    }

    /**
     * 刷新token
     *
     * @param token 旧token
     * @return 新token
     */
    @Override
    public String refreshToken(T token) {
        return generateToken(token);
    }

}
