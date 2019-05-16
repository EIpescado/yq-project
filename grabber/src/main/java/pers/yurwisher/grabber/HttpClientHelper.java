package pers.yurwisher.grabber;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yq
 * @date 2018/07/21 09:27
 * @description http clients 实现
 * @since V1.0.0
 */
public class HttpClientHelper {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientHelper.class);
    private static final Header JSON_CONTENT_TYPE = new BasicHeader("Content-type","application/json;charset=utf-8");

    /**连接等待时间*/
    @Getter
    @Setter
    private int connectTimeOut = 5000;
    /**请求连接超时时间*/
    @Getter
    @Setter
    private int requestTimeOut = 1000;
    /**数据传输时间*/
    @Getter
    @Setter
    private int socketTimeOut = 5000;
    /**编码格式*/
    @Getter
    @Setter
    private String charset = "UTF-8";
    /**
     * 请求客户端
     */
    private CloseableHttpClient httpClient ;
    /**
     * 自定义请求参数配置
     */
    private RequestConfig requestConfig;

    /**
     * 不需要导入证书，SSL信任所有证书，使用该方法
     */
    public CloseableHttpClient getDefaultClient() {
        if(httpClient == null){
            try {
                SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
                SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
                return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
            } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
                logger.error("add SSL support error",e);
                httpClient = HttpClients.createDefault();
            }
        }
        return httpClient ;
    }

    /**
     * 请求参数配置
     */
    private RequestConfig getDefaultRequestConfig() {
        if(requestConfig == null){
            //自定义请求参数配置
            requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(requestTimeOut)
                    .setSocketTimeout(socketTimeOut)
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
     */
    public String sendGet(String url, Header... header){
        return  sendGet(url,getDefaultRequestConfig(),getDefaultClient(),header);
    }

    /**
     * 发送get请求
     * @param url 请求地址
     * @param config 自定义请求参数配置
     * @return 请求结果
     */
    public String sendGet(String url, RequestConfig config, CloseableHttpClient client, Header... header){
        CloseableHttpResponse response;
        HttpEntity entity ;
        String content = null;
        // 构建请求地址 创建httpGet.
        HttpGet httpget = null;
        try {
            httpget = new HttpGet(url);
            //设置请求参数
            httpget.setConfig(config);
            httpget.setHeaders(header);
            // 执行get请求.
            response = client.execute(httpget);
            final StatusLine statusLine = response.getStatusLine();
            //响应码
            int responseCode = statusLine.getStatusCode();
            // 获取响应实体
            entity = response.getEntity();
            logger.debug("response code: {},reason phrase:{}",responseCode,statusLine.getReasonPhrase());
            //响应成功
            if(HttpStatus.SC_OK == responseCode){
                if(entity != null){
                    content  =  EntityUtils.toString(entity, charset);
                }
                EntityUtils.consume(entity);
            }
            return content;
        } catch (IOException e) {
           logger.debug("get request error",e);
        } finally {
            if(httpget != null){
                //释放连接
                httpget.releaseConnection();
            }
        }
        return content;
    }

    public String postJSON(String url, String jsonParams){
        return postJSON(url,jsonParams,getDefaultClient());
    }

    public String postJSON(String url, String jsonParams, Header... header){
        return postJSON(url,jsonParams,getDefaultClient(),header);
    }

    public String postJSON(String url,String jsonParams,CloseableHttpClient client){
        return postJSON(url,jsonParams,client,JSON_CONTENT_TYPE);
    }

    /**
     *  post json请求
     * @param url 请求地址
     * @param jsonParams json 参数
     * @param client client
     * @param headers 请求头
     * @return 接口返回
     */
    public String postJSON(String url,String jsonParams,CloseableHttpClient client,Header...headers){
        // 创建httpPost
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeaders(headers);
        if(Utils.isNotEmpty(jsonParams)){
            StringEntity stringEntity = new StringEntity(jsonParams,charset);
            httpPost.setEntity(stringEntity);
        }
        return post(client,httpPost);
    }

    public String post(CloseableHttpClient httpClient,HttpPost httpPost){
        CloseableHttpResponse response ;
        HttpEntity entity ;
        String content = null;
        try {
            response = httpClient.execute(httpPost);
            final StatusLine statusLine = response.getStatusLine();
            logger.debug("status code: {},reason phrase:{}",statusLine.getStatusCode(),statusLine.getReasonPhrase());
            // 获取响应实体
            entity = response.getEntity();
            if(entity != null){
                content  =  EntityUtils.toString(entity,charset);
            }
            return content;
        } catch (IOException e) {
            logger.error("post request error",e);
        }finally {
            if(httpPost != null){
                httpPost.releaseConnection();
            }
        }
        return null ;
    }

    /**
     * 创建请求头
     * @param name 名称
     * @param value 值
     * @return 请求头
     */
    public Header createHeader(String name, String value){
        return new BasicHeader(name,value);
    }

    /**
     * 表单提交post
     * @param url 地址
     * @param pairs 表单数据
     * @return String
     */
    public String postForm(String url, BasicNameValuePair...pairs){
        // 创建httpPost
        HttpPost httpPost = new HttpPost(url);
        UrlEncodedFormEntity urlEncodedFormEntity = buildFormParams(pairs);
        if(urlEncodedFormEntity != null){
            httpPost.setEntity(urlEncodedFormEntity);
        }
        return post(getDefaultClient(),httpPost);
    }

    /**
     * 构建post提交表单参数,url编码,utf-8
     * @param pairs 表单参数
     */
    public UrlEncodedFormEntity buildFormParams(BasicNameValuePair...pairs){
        UrlEncodedFormEntity uefEntity = null ;
        // 创建参数队列
        List<NameValuePair> formParams = new ArrayList<>();
        if(pairs != null && pairs.length > 0){
            for (BasicNameValuePair pair : pairs){
                formParams.add(pair);
            }
        }
        try {
            uefEntity = new UrlEncodedFormEntity(formParams,charset);
        } catch (UnsupportedEncodingException e) {
            logger.error("");
        }
        return  uefEntity ;
    }
}
