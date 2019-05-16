package pers.yurwisher.token;

import pers.yurwisher.token.exception.TokenException;

/**
 * @author yq
 * @date 2018/12/12 17:19
 * @description token接口
 * @since V1.0.0
 */
public interface ITokenService {

    /**
     * 生成token
     * @param subject
     * @param token
     * @return
     */
    String generateToken(String subject,Token token);

    /**
     * 解析token获取参数对象
     * @param jwsToken token
     */
    Token parseToken(String jwsToken) throws TokenException;


    /**
     * 生成token
     * @param subject
     * @param token
     * @param expireTime 过期时间
     * @return
     */
    String generateToken(String subject, Token token,Long expireTime);
}
