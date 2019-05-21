package pers.yurwisher.wisp.utils;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

/**
 * @author yq
 * @date 2018/12/29 16:31
 * @description RSA
 * @since V1.0.0
 */
public class RSAUtils {
    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "RSA";
    /**
     * 加解密算法
     */
    private static final String SPECIFIC_KEY_ALGORITHM = "RSA/ECB/PKCS1Padding";
    /**
     * 签名算法
     */
    private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
    /**
     * 默认KEY大小
     */
    private static final int DEFAULT_KEY_SIZE = 1024;
    /**
     * 分段加密一段对大长度
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * 默认编码
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 密钥工厂
     */
    private static KeyFactory KEY_FACTORY;

    static {
        try {
            KEY_FACTORY = KeyFactory.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成公私钥对
     *
     * @param useRandomString 是否使用随机字符串
     * @param randomStr       随机字符串
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair getKeyPair(boolean useRandomString, String... randomStr) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        if (useRandomString) {
            SecureRandom ranDom = new SecureRandom(randomStr[0].getBytes());
            keyPairGenerator.initialize(DEFAULT_KEY_SIZE, ranDom);
        } else {
            keyPairGenerator.initialize(DEFAULT_KEY_SIZE);
        }
        // 生成密钥组
        return keyPairGenerator.generateKeyPair();
    }


    /**
     * 获取私钥 base64加密
     *
     * @param keyPair 密钥对
     * @return base64加密后私钥
     */
    public static String getPrivateKey(KeyPair keyPair) {
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return CodeUtils.encryptBASE64(privateKey.getEncoded());
    }

    /**
     * 获取公钥 base64加密
     *
     * @param keyPair 密钥对
     * @return base64加密后公钥
     */
    public static String getPublicKey(KeyPair keyPair) {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        return CodeUtils.encryptBASE64(publicKey.getEncoded());
    }

    /**
     * 私钥对数据进行签名
     * @param data          需要签名的数据
     * @param privateKeyStr 私钥（使用BASE64进行编码）
     * @return 返回加签名后的BASE64编码的字符串
     * @throws Exception
     */
    public static String signByPrivateKey(String data, String privateKeyStr) throws Exception {
        //base64 解密
        byte[] privateKeyByte = CodeUtils.decryptBASE64(privateKeyStr);
        //私钥 用 PKCS#8 编码
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyByte);
        //根据给定的算法生成一个私钥对象
        RSAPrivateKey privateKey = (RSAPrivateKey) KEY_FACTORY.generatePrivate(keySpec);
        //根据算法获取一个签名对象
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        //签名
        signature.update(data.getBytes(DEFAULT_CHARSET));
        //返回签名后的字节数组 并用BASE64加密
        return CodeUtils.encryptBASE64(signature.sign());
    }

    /**
     * 公钥数据验签
     *
     * @param data         需要进行验签的原始数据
     * @param publicKeyStr 公钥（BASE64编码字符串）
     * @param sign         签名 （BASE64编码字符串）
     * @return 校验结果
     */
    public static boolean verifyByPublicKey(String data, String publicKeyStr, String sign) {
        try {
            byte[] publicKeyByte = CodeUtils.decryptBASE64(publicKeyStr);
            //公钥 用 X509#8 编码
            X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(publicKeyByte);
            //根据给定的算法生成一个公钥对象
            RSAPublicKey publicKey = (RSAPublicKey) KEY_FACTORY.generatePublic(encodedKeySpec);
            //根据算法获取一个签名对象
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            //初始化签名对象
            signature.initVerify(publicKey);
            signature.update(data.getBytes(DEFAULT_CHARSET));
            //验证是否签名
            return signature.verify(CodeUtils.decryptBASE64(sign));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 公钥加密
     *
     * @param data         数据
     * @param publicKeyStr 公钥
     * @return 加密后结果
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, String publicKeyStr) throws Exception {
        if (null == data) {
            return null;
        }
        byte[] dataB = data.getBytes(DEFAULT_CHARSET);
        byte[] publicKeyByte = CodeUtils.decryptBASE64(publicKeyStr);
        //公钥 用 X509#8 编码
        X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(publicKeyByte);
        RSAPublicKey publicKey = (RSAPublicKey) KEY_FACTORY.generatePublic(encodedKeySpec);
        //根据算法获取加解密器
        Cipher cipher = Cipher.getInstance(SPECIFIC_KEY_ALGORITHM);
        //用指定的密钥和模式初始化 Cipher 对象
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = dataB.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        try {
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(dataB, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(dataB, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = out.toByteArray();
            return CodeUtils.encryptBASE64(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
        return null;
    }

    /**
     * 私钥解密
     *
     * @param data          密文
     * @param privateKeyStr 私钥
     * @return 解密后结果
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data, String privateKeyStr) throws Exception {
        if (null == data) {
            return null;
        }
        byte[] dataB = CodeUtils.decryptBASE64(data);
        byte[] privateKeyByte = CodeUtils.decryptBASE64(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyByte);
        //生成私钥对象
        RSAPrivateKey privateKey = (RSAPrivateKey) KEY_FACTORY.generatePrivate(keySpec);
        //根据算法获取加解密器
        Cipher cipher = Cipher.getInstance(SPECIFIC_KEY_ALGORITHM);
        //用指定的密钥和模式初始化 Cipher 对象
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        //模长
        int keyLen = privateKey.getModulus().bitLength() / 8;
        byte[] decryptedData;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            int dataLength = dataB.length;
            for (int i = 0; i < dataLength; i += keyLen) {
                int decryptLength = dataLength - i < keyLen ? dataLength - i
                        : keyLen;
                byte[] doFinal = cipher.doFinal(dataB, i, decryptLength);
                bout.write(doFinal);
            }
            decryptedData = bout.toByteArray();
        } finally {
            bout.close();
        }
        return CodeUtils.encryptBASE64(decryptedData);
    }

    /**
     * 串接arr参数，生成sha1 digest
     */
    public static String gen(String... arr) {
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (String a : arr) {
            sb.append(a);
        }
        return DigestUtils.sha1Hex(sb.toString());
    }

    public static void y() throws Exception {
        KeyPair map = getKeyPair(false);
        String privateKey = getPrivateKey(map);
        System.out.println("私钥:" + privateKey);
        String publicKey = getPublicKey(map);
        System.out.println("公钥:" + publicKey);

        String password = "password";

        //公钥加密
        String afterPublicKeyen = encryptByPublicKey(password, publicKey);
        System.out.println("加密后字符串:" + afterPublicKeyen);
        //私钥解密
        String afterPride = decryptByPrivateKey(afterPublicKeyen, privateKey);

        System.out.println(System.currentTimeMillis() + " base解密:" + CodeUtils.decodeBASE64(afterPride));

        System.out.println("============================================================");

        String data = "appId1timestamp" + System.currentTimeMillis() + "nonce123456" + privateKey;
        String sign = signByPrivateKey(data, privateKey);
        System.out.println("签名:" + sign);
        System.out.println("签名:" + verifyByPublicKey(data + "1", publicKey, sign));
        System.out.println("签名:" + CodeUtils.sha256Hex(sign));
    }

    public static void main(String[] args) throws Exception {
        y();
    }
}
