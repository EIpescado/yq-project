package pers.yurwisher.token;

import pers.yurwisher.token.exception.TokenException;

/**
 * @author yq
 * @date 2018/12/12 17:19
 * @description token接口
 * @since V1.0.0
 */
public interface ITokenService<T extends Token> {

    /**
     * 生成token
     * @param token 对象
     */
    String generateToken(T token);

    /**
     * 解析token获取参数对象
     * @param jwsToken token字符串
     * @return token
     * @exception TokenException 转化异常
     */
    T parseToken(String jwsToken) throws TokenException;

    /**
     * 解析token获取参数对象
     * @param jwsToken token字符串
     * @param tokenClass 自定义token类
     * @return token
     * @exception  TokenException 转化异常
     */
    Token parseToken(String jwsToken,Class<? extends Token> tokenClass) throws TokenException;

    /**
     * 生成token
     * @param token token对象
     * @param expireTime 过期时间
     * @return token字符串
     */
    String generateToken(T token,Long expireTime);

    /**
     * 刷新token
     * @param token token对象
     * @return token字符串
     */
    String refreshToken(T token);

    /**
     * 刷新token
     * @param token token对象
     * @param expireTime 过期时间
     * @return token字符串
     */
    String refreshToken(T token,Long expireTime);

    /**
     * 是否可以刷新token
     * @param token  token
     * @return boolean
     */
    boolean canRefresh(T token) ;
}
