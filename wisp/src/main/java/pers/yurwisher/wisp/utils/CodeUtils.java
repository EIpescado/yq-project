package pers.yurwisher.wisp.utils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by yq on 2017/02/06 10:51.
 * 编解码工具类
 */
public class CodeUtils {

    private CodeUtils() {
    }

    /**
     * BASE64解密 ->返回数组
     * @param key
     */
    public static byte[] decryptBASE64(String key){
        Base64 base64 = new Base64();
        return base64.decode(key.getBytes());
    }

    /**
     * BASE64解密 ->返回字符串
     * @param key
     */
    public static String decodeBASE64(String key){
        Base64 base64 = new Base64();
        return new String(base64.decode(key.getBytes()));
    }

    /**
     * BASE64加密
     * @param key
     * @return 加密后字符串
     */
    public static String encryptBASE64(byte[] key) {
        Base64 base64 = new Base64();
        return base64.encodeToString(key);
    }

    /**
     * BASE64加密 url安全的 地址安全的 使用 - 和 _ 替代 + /
     * @param key
     */
    public static String encodeURLSafeStringBASE64(byte[] key) {
        return Base64.encodeBase64URLSafeString(key);
    }

    /**
     * Hex加密
     * @param key
     */
    public static String encodeHex(String key){
       return Hex.encodeHexString(key.getBytes());
    }

    /**
     * Hex解密
     * @param  key
     */
    public static String decodeHex(String key) throws Exception{
        Hex hex  = new Hex();
        return new String(hex.decode(key.getBytes()));
    }

    /**
     * MD5加密
     * @param key
     */
    public static String MD5(String key){
       return new String(DigestUtils.md5(key));
    }

    /**
     * MD5加密 转Hex(固定长度)
     * @param key
     */
    public static String md5Hex(String key){
        return DigestUtils.md5Hex(key);
    }

    /**
     * sha-1加密
     * @param key
     */
    public static String sha1(String key){
        return new String(DigestUtils.sha1(key.getBytes()));
    }

    /**
     * sha-1加密 转Hex(固定长度) 大写
     * @param key
     */
    public static String sha1Hex(String key){
        return DigestUtils.sha1Hex(key.getBytes()).toUpperCase();
    }

    /**
     * sha-256加密 转Hex(固定长度) 大写
     * @param key
     */
    public static String sha256Hex(String key){
        return DigestUtils.sha256Hex(key.getBytes()).toUpperCase();
    }

}
