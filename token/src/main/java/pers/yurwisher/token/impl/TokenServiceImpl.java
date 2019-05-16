package pers.yurwisher.token.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import pers.yurwisher.token.ITokenService;
import pers.yurwisher.token.Token;
import pers.yurwisher.token.exception.TokenException;
import pers.yurwisher.token.TokenHelper;

import java.util.Date;
import java.util.Map;

/**
 * @author yq
 * @date 2018/12/10 15:21
 * @description tokenService
 * @since V1.0.0
 */
public class TokenServiceImpl implements ITokenService {

    private TokenHelper tokenHelper;

    public TokenServiceImpl(TokenHelper tokenHelper){
        this.tokenHelper = tokenHelper;
    }

    /**
     * 生成token
     */
    @Override
    public String generateToken(String subject, Token token) {
        return generateToken(subject,token.toJSON(),tokenHelper.getExpireTime());
    }

    /**
     * 解析token获取参数对象
     * @param jwsToken token
     */
    @Override
    public Token parseToken(String jwsToken) {
        try{
            Jws<Claims> claimsJws = Jwts.parser()
                    //验证签发者
                    .requireIssuer(tokenHelper.getIssuer())
                    .setSigningKey(tokenHelper.getSigningKey())
                    .parseClaimsJws(jwsToken);
            if(claimsJws != null){
                Claims claims = claimsJws.getBody() ;
                if(claims != null && !claims.isEmpty()){
                    return ((JSONObject) JSON.toJSON(claims)).toJavaObject(tokenHelper.getCustomTokenClass());
                }
            }
        }catch (Exception e) {
            throw new TokenException("token parse fail: " + e.getLocalizedMessage() );
        }
        return null;
    }

    /**
     * 生成token
     * @param subject 所有人
     * @param claims 需要缓存的一些基础数据字段
     * @param expireTime 过期时间
     * @return token
     */
    private String generateToken(String subject, Map<String, Object> claims, Long expireTime){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //头部(Header) 载荷（payload） Audience 接收方 用户
        JwtBuilder builder = Jwts.builder()
                //自定义属性
                .setClaims(claims)
                .setHeaderParam("typ", "JWT")
                //签发者
                .setIssuer(tokenHelper.getIssuer())
                //代表这个JWT的主体，即所有人
                .setSubject(subject)
                //接收者
                .setAudience(tokenHelper.getAudience())
                //jwt的签发时间
                .setIssuedAt(new Date())
                //签名算法及密钥
                .signWith(tokenHelper.getSignatureAlgorithm(), tokenHelper.getSigningKey());
        if (expireTime >= 0) {
            long expMillis = nowMillis + expireTime;
            Date exp = new Date(expMillis);
            //设置过期时间
            builder.setExpiration(exp).setNotBefore(now);
        }
        return builder.compact();
    }

    @Override
    public String generateToken(String subject, Token token, Long expireTime) {
        return generateToken(subject,token.toJSON(),expireTime);
    }
}
