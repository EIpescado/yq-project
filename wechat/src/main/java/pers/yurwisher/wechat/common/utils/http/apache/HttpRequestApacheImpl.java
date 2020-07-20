package pers.yurwisher.wechat.common.utils.http.apache;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.wechat.common.utils.Utils;
import pers.yurwisher.wechat.common.utils.http.HttpRequest;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author yq
 * @date 2018/07/21 09:27
 * @description http clients 实现
 * @since V1.0.0
 */
public class HttpRequestApacheImpl implements HttpRequest {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestApacheImpl.class);

    private HttpRequestApacheImpl(){}

    /**
     * 单例
     */
    public static final  HttpRequest  INSTANCE = new HttpRequestApacheImpl();

    /**连接等待时间*/
    private static final int CONNECT_TIMEOUT  = 5000 ;

    /**请求连接超时时间*/
    private static final int CONNECTION_REQUEST_TIMEOUT  = 1000 ;

    /**数据传输时间*/
    private static final int SOCKET_TIMEOUT  = 5000 ;

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 请求客户端
     */
    private CloseableHttpClient httpClient ;
    /**
     * 自定义请求参数配置
     */
    private RequestConfig requestConfig;

    private static final String TOKEN_URL_KEY = "?access_token=";

    /**
     * 添加token参数到请求地址
     * @param url 请求地址
     * @param accessToken token
     * @return 请求地址
     */
    public static String addTokenToUrl(String url, String accessToken) {
        return url + TOKEN_URL_KEY + accessToken;
    }


    @Override
    public String get(String url) {
        return sendGet(url);
    }

    @Override
    public String getWithToken(String url, String token) {
        return sendGet(addTokenToUrl(url,token));
    }

    @Override
    public String getWithToken(String url, String token, String otherParams) {
        return sendGet(addTokenToUrl(url,token) + otherParams);
    }

    @Override
    public String post(String url, String jsonString) {
        return postJSON(url,jsonString,getDefaultClient());
    }

    @Override
    public String postWithToken(String url, String token, String jsonString) {
        return postJSON(addTokenToUrl(url,token),jsonString,getDefaultClient());
    }

    @Override
    public String uploadFile(String url, File file,String mediaKey) {
        if(file != null &&  file.exists()){
            // 创建httpPost
            HttpPost httpPost = new HttpPost(url);
            HttpEntity entity = MultipartEntityBuilder
                    .create()
                    .addBinaryBody(mediaKey, file)
                    .setMode(HttpMultipartMode.RFC6532)
                    .build();
            httpPost.setEntity(entity);
            return post(getDefaultClient(),httpPost);
        }
        return null;
    }

    /**
     * 不需要导入证书，SSL信任所有证书，使用该方法
     */
    public  CloseableHttpClient getDefaultClient() {
        if(httpClient == null){
            try {
                SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
                SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
                return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
            } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
                logger.error("异常",e);
            }
            httpClient = HttpClients.createDefault();
        }
        return httpClient ;
    }

    /**
     * 请求参数配置
     */
    public RequestConfig getDefaultRequestConfig() {
        if(requestConfig == null){
            //自定义请求参数配置
            requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    .build();
        }
        return requestConfig;
    }

    /**
     * 发送get请求(默认)
     * @param url 请求地址
     */
    public String sendGet(String url){
        return  sendGet(url,getDefaultRequestConfig(),getDefaultClient());
    }

    /**
     * 发送get请求
     * @param url 请求地址
     * @param config 自定义请求参数配置
     * @return 请求结果
     */
    public String sendGet(String url, RequestConfig config,CloseableHttpClient client){
        CloseableHttpResponse response;
        HttpEntity entity ;
        String content = "";
        // 构建请求地址 创建httpGet.
        HttpGet httpget = null;
        try {
            httpget = new HttpGet(url);
            //设置请求参数
            httpget.setConfig(config);
            logger.info("get请求: {}",httpget.getURI());
            // 执行get请求.
            response = client.execute(httpget);
            final StatusLine statusLine = response.getStatusLine();
            //响应码
            int responseCode = statusLine.getStatusCode();
            // 获取响应实体
            entity = response.getEntity();
            logger.info("响应状态: {},原因:{}",responseCode,statusLine.getReasonPhrase());
            //响应成功
            if(HttpStatus.SC_OK == responseCode){
                if(entity != null){
                    content  =  EntityUtils.toString(entity, DEFAULT_CHARSET);
                }
                EntityUtils.consume(entity);
            }
            return content;
        } catch (IOException e) {
           logger.error("get请求异常",e);
        } finally {
            if(httpget != null){
                //释放连接
                httpget.releaseConnection();
            }
        }
        logger.info("返回正文: {}",content);
        return content;
    }

    /**
     * post json请求
     * @param url 请求地址
     * @param jsonParams json 参数
     */
    public  String postJSON(String url,String jsonParams,CloseableHttpClient client){
        // 创建httpPost
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-type","application/json;charset=utf-8");
        if(Utils.isNotEmpty(jsonParams)){
            StringEntity stringEntity = new StringEntity(jsonParams,DEFAULT_CHARSET);
            httpPost.setEntity(stringEntity);
        }
        return post(client,httpPost);
    }

    public String post(CloseableHttpClient httpClient,HttpPost httpPost){
        CloseableHttpResponse response ;
        HttpEntity entity ;
        String content = "";
        try {
            response = httpClient.execute(httpPost);

            final StatusLine statusLine = response.getStatusLine();
            logger.info("响应状态: {},原因:{}",statusLine.getStatusCode(),statusLine.getReasonPhrase());

            // 获取响应实体
            entity = response.getEntity();
            if(entity != null){
                content  =  EntityUtils.toString(entity,DEFAULT_CHARSET);
            }
            return content;
        } catch (IOException e) {
            logger.error("post请求异常",e);
        }finally {
            if(httpPost != null){
                httpPost.releaseConnection();
            }
        }
        logger.info("返回正文: {}",content);
        return content ;
    }

    private String buildOtherParamsUrl(Map<String, Object> otherParams){
        if (Utils.isNotEmpty(otherParams)){
            StringBuilder sb = new StringBuilder();
            otherParams.forEach((k,v) ->
                    sb.append(k).append("=").append(v)
            );
            return sb.toString();
        }
        return "";
    }
}
