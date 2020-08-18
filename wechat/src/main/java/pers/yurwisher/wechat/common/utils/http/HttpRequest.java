package pers.yurwisher.wechat.common.utils.http;


import java.io.InputStream;

/**
 * @author yq
 * @date 2018/07/21 09:20
 * @description http请求
 * @since V1.0.0
 */
public interface HttpRequest {

    /**
     * get请求
     * @param url 地址
     * @return 请求结果
     */
    String get(String url);

    /**
     * get请求
     * @param url 地址
     * @param token token
     * @return 请求结果
     */
    String getWithToken(String url, String token);

    /**
     * get请求
     * @param url 地址
     * @param token token
     * @param otherParams
     * @return 请求结果
     */
    String getWithToken(String url, String token,String otherParams);

    /**
     * post 请求
     * @param url 地址
     * @param jsonString json
     * @return 请求结果
     */
    String post(String url, String jsonString);

    /**
     * post 请求
     * @param url 地址
     * @param token token
     * @param jsonString json
     * @return 请求结果
     */
    String postWithToken(String url, String token, String jsonString);


    /**
     * 上传文件 post
     * @param url 地址
     * @param inputStream 输入流
     * @param mediaKey 文件标识
     * @return 请求结果
     */
    String uploadFile(String url, InputStream inputStream, String mediaKey);

    /**
     * 上传文件 post
     * @param url 地址
     * @param token token
     * @param inputStream 输入流
     * @param mediaKey 文件标识
     * @return 请求结果
     */
    String uploadFileWithToken(String url, String token,InputStream inputStream, String mediaKey);

    /**
     * 上传文件 post
     * @param url 地址
     * @param inputStream 输入流
     * @param mediaKey 文件标识
     * @return 请求结果
     */
    String uploadFile(String url,InputStream inputStream, String mediaKey,String otherKey,String otherParams);
}
