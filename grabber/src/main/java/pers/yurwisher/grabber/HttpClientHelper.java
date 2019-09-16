package pers.yurwisher.grabber;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
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
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yq
 * @date 2018/07/21 09:27
 * @description http clients 实现
 * @since V1.0.0
 */
public class HttpClientHelper {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientHelper.class);

    private HttpClientHelper() {
    }

    private static class HttpClientHolder {
        public static final HttpClientHelper INSTANCE = new HttpClientHelper();
    }

    public static HttpClientHelper getInstance() {
        return HttpClientHolder.INSTANCE;
    }

    /**
     * 连接等待时间
     */
    private static final int CONNECT_TIMEOUT = 8000;
    /**
     * 请求连接超时时间
     */
    private static final int CONNECTION_REQUEST_TIMEOUT = 8000;
    /**
     * 数据传输时间
     */
    private static final int SOCKET_TIMEOUT = 8000;
    /**
     * 默认编码
     */
    private static final String DEFAULT_CHARSET = "UTF-8";
    /**
     * 默认请求客户端
     */
    private CloseableHttpClient defaultHttpClient;
    /**
     * 默认请求参数配置
     */
    private RequestConfig defaultRequestConfig;
    /**
     * 默认响应结果处理
     */
    private DisposeResponse defaultDisposeResponse;

    /**
     * 不需要导入证书，SSL信任所有证书，使用该方法
     */
    public CloseableHttpClient getDefaultClient() {
        if (defaultHttpClient == null) {
            try {
                SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
                SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
                defaultHttpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
            } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
                logger.error("创建http client失败", e);
                throw new GrabException("create http client error");
            }
        }
        return defaultHttpClient;
    }

    /**
     * 请求参数配置
     */
    public RequestConfig getDefaultRequestConfig() {
        if (defaultRequestConfig == null) {
            //自定义请求参数配置
            defaultRequestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    .build();
        }
        return defaultRequestConfig;
    }

    /**
     * 基于base auth的客户端,需要自己缓存对象
     */
    public CloseableHttpClient createBaseAuthClient(String userName, String password) {
        try {
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build());
            CredentialsProvider base = new BasicCredentialsProvider();
            base.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
            return HttpClients.custom()
                    .setSSLSocketFactory(sslConnectionSocketFactory)
                    .setDefaultCredentialsProvider(base)
                    .build();
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            logger.error("创建http client失败", e);
            throw new GrabException("create http client error");
        }
    }

    /**
     * 发送get请求(默认)
     *
     * @param url 请求地址
     */
    public String sendGet(String url) {
        return sendGet(url, getDefaultRequestConfig(), getDefaultClient(), getDefaultDisposeResponse());
    }

    /**
     * 发送get请求
     *
     * @param url     请求地址
     * @param headers 请求头
     */
    public String sendGet(String url, Header... headers) {
        return sendGet(url, getDefaultRequestConfig(), getDefaultClient(), getDefaultDisposeResponse(), headers);
    }

    /**
     * 发送get请求
     *
     * @param url     请求地址
     * @param config  自定义请求配置
     * @param client  client
     * @param headers headers
     * @return 请求结果
     */
    public String sendGet(String url, RequestConfig config, CloseableHttpClient client, DisposeResponse disposeResponse, Header... headers) {
        // 构建请求地址 创建httpGet.
        HttpGet httpget = null;
        try {
            httpget = new HttpGet(url);
            //设置请求参数
            httpget.setConfig(config);
            if (headers != null && headers.length > 0) {
                httpget.setHeaders(headers);
            }
            // 执行get请求.
            CloseableHttpResponse response = client.execute(httpget);
            return this.disposeResponse(response, disposeResponse);
        } catch (IOException e) {
            logger.error("请求异常:{}", e);
            throw new GrabException("get request error");
        } finally {
            if (httpget != null) {
                //释放连接
                httpget.releaseConnection();
            }
        }
    }

    /**
     * 下载附件
     *
     * @param url             下载地址
     * @param disposeResponse 响应成功对响应进行处理
     * @param headers         请求头
     */
    public void downloadFile(String url, DisposeResponse disposeResponse, Header... headers) {
        this.downloadFile(url, getDefaultRequestConfig(), getDefaultClient(), disposeResponse, headers);
    }

    /**
     * 下载附件
     *
     * @param url             下载地址
     * @param config          配置
     * @param client          client
     * @param disposeResponse 响应成功对响应进行处理
     * @param headers         请求头
     */
    public void downloadFile(String url, RequestConfig config, CloseableHttpClient client, DisposeResponse disposeResponse, Header... headers) {
        CloseableHttpResponse response;
        // 构建请求地址 创建httpGet.
        HttpGet httpget = null;
        try {
            httpget = new HttpGet(url);
            httpget.setConfig(config);
            //设置请求参数
            httpget.setHeaders(headers);
            // 执行get请求.
            response = client.execute(httpget);
            this.disposeResponse(response, disposeResponse);
        } catch (IOException e) {
            logger.error("请求异常", e);
            throw new GrabException("request error");
        } finally {
            if (httpget != null) {
                //释放连接
                httpget.releaseConnection();
            }
        }
    }

    /**
     * post json请求
     *
     * @param url        请求地址
     * @param jsonParams json 参数
     */
    public String postJSON(String url, String jsonParams) {
        return postJSON(url, jsonParams, getDefaultClient(), getDefaultRequestConfig(), getDefaultDisposeResponse(), null);
    }

    /**
     * post json请求
     *
     * @param url        请求地址
     * @param jsonParams json 参数
     */
    public String postJSON(String url, String jsonParams, Map<String, String> headers) {
        return postJSON(url, jsonParams, getDefaultClient(), getDefaultRequestConfig(), getDefaultDisposeResponse(), headers);
    }

    /**
     * post json请求
     *
     * @param url        请求地址
     * @param jsonParams json 参数
     * @param config     请求自定义配置
     */
    public String postJSON(String url, String jsonParams, RequestConfig config) {
        return postJSON(url, jsonParams, getDefaultClient(), config, getDefaultDisposeResponse(), null);
    }

    /**
     * post 请求
     *
     * @param url             请求地址
     * @param jsonParams      json参数
     * @param httpClient      客户端
     * @param config          请求配置
     * @param disposeResponse 响应处理
     * @param headers         请求头
     * @return 响应结果
     */
    public String postJSON(String url, String jsonParams, CloseableHttpClient httpClient, RequestConfig config, DisposeResponse disposeResponse, Map<String, String> headers) {
        Map<String, String> trueHeaders = headers;
        if (Utils.isEmpty(trueHeaders)) {
            trueHeaders = new HashMap<>(1);
        }
        trueHeaders.put("Content-Type", "application/json;charset=UTF-8");
        return post(url, () -> {
            if (Utils.isNotEmpty(jsonParams)) {
                return new StringEntity(jsonParams, DEFAULT_CHARSET);
            }
            return null;
        }, httpClient, config, disposeResponse, trueHeaders);
    }

    /**
     * 表单提交
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 响应结果
     */
    public String postForm(String url, Map<String, Object> params) {
        return postForm(url, params, getDefaultClient(), getDefaultRequestConfig(), getDefaultDisposeResponse(), null);
    }

    /**
     * 表单提交
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 响应结果
     */
    public String postForm(String url, Map<String, Object> params, CloseableHttpClient httpClient) {
        return postForm(url, params, httpClient, getDefaultRequestConfig(), getDefaultDisposeResponse(), null);
    }

    /**
     * 表单提交
     *
     * @param url             请求地址
     * @param params          请求参数
     * @param disposeResponse 响应处理
     * @return 响应结果
     */
    public String postForm(String url, Map<String, Object> params, DisposeResponse disposeResponse) {
        return postForm(url, params, getDefaultClient(), getDefaultRequestConfig(), disposeResponse, null);
    }

    /**
     * 表单提交
     *
     * @param url             请求地址
     * @param formParams      参数
     * @param headers         请求头
     * @return 响应结果
     */
    public String postForm(String url, List<NameValuePair> formParams, Map<String, String> headers) {
        return post(url, () -> {
            if (Utils.isNotEmpty(formParams)) {
                return new UrlEncodedFormEntity(formParams);
            }
            return null;
        }, getDefaultClient(), getDefaultRequestConfig(), getDefaultDisposeResponse(), headers);
    }

    /**
     * 表单提交
     *
     * @param url             请求地址
     * @param formParams      参数
     * @return 响应结果
     */
    public String postForm(String url, List<NameValuePair> formParams) {
        return post(url, () -> {
            if (Utils.isNotEmpty(formParams)) {
                return new UrlEncodedFormEntity(formParams);
            }
            return null;
        }, getDefaultClient(), getDefaultRequestConfig(), getDefaultDisposeResponse(), null);
    }

    /**
     * 表单提交
     *
     * @param url             请求地址
     * @param formParams      参数
     * @param httpClient      客户端
     * @param config          请求配置
     * @param disposeResponse 响应处理
     * @param headers         请求头
     * @return 响应结果
     */
    public String postForm(String url, List<NameValuePair> formParams, CloseableHttpClient httpClient, RequestConfig config, DisposeResponse disposeResponse, Map<String, String> headers) {
        return post(url, () -> {
            if (Utils.isNotEmpty(formParams)) {
                return new UrlEncodedFormEntity(formParams);
            }
            return null;
        }, httpClient, config, disposeResponse, headers);
    }

    /**
     * 表单提交
     *
     * @param url             请求地址
     * @param params          参数
     * @param httpClient      客户端
     * @param config          请求配置
     * @param disposeResponse 响应处理
     * @param headers         请求头
     * @return 响应结果
     */
    public String postForm(String url, Map<String, Object> params, CloseableHttpClient httpClient, RequestConfig config, DisposeResponse disposeResponse, Map<String, String> headers) {
        return post(url, () -> {
            if (Utils.isNotEmpty(params)) {
                //map转表单参数
                List<NameValuePair> formParams = params.entrySet().stream()
                        .map(entry -> new BasicNameValuePair(entry.getKey(), Utils.null2EmptyWithTrimNew(entry.getValue())))
                        .collect(Collectors.toList());
                return new UrlEncodedFormEntity(formParams);
            }
            return null;
        }, httpClient, config, disposeResponse, headers);
    }

    public String post(String url, ParamsBuilder builder, CloseableHttpClient httpClient, RequestConfig config, DisposeResponse disposeResponse, Map<String, String> headers) {
        // 创建httpPost
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        //设置请求头
        if (Utils.isNotEmpty(headers)) {
            headers.forEach((key, value) ->
                    httpPost.setHeader(new BasicHeader(key, value))
            );
        }
        try {
            //构建请求参数
            HttpEntity paramsEntity = builder.build();
            if (paramsEntity != null) {
                httpPost.setEntity(paramsEntity);
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            return this.disposeResponse(response, disposeResponse);
        } catch (IOException e) {
            logger.error("post form 异常", e);
            throw new GrabException("post request error");
        } finally {
            httpPost.releaseConnection();
        }
    }

    private String disposeResponse(CloseableHttpResponse response, DisposeResponse disposeResponse) {
        final StatusLine statusLine = response.getStatusLine();
        //响应码
        int responseCode = statusLine.getStatusCode();
        logger.debug("响应状态: {},原因:{}", responseCode, statusLine.getReasonPhrase());
        //响应成功
        if (HttpStatus.SC_OK == responseCode) {
            return disposeResponse.dispose(response);
        } else {
            throw new GrabException("response status not ok , code was:" + responseCode);
        }
    }

    /**
     * 默认响应处理接口,返回字符串
     *
     * @return 默认响应处理接口
     */
    private DisposeResponse getDefaultDisposeResponse() {
        if (defaultDisposeResponse == null) {
            defaultDisposeResponse = res -> {
                String content = "";
                // 获取响应实体
                HttpEntity entity = res.getEntity();
                try {
                    if (entity != null) {
                        content = EntityUtils.toString(entity, DEFAULT_CHARSET);
                    }
                    EntityUtils.consume(entity);
                    return content;
                } catch (IOException e) {
                    logger.error("处理响应结果失败", e);
                    throw new GrabException("dispose response error");
                }
            };
        }
        return defaultDisposeResponse;
    }

    public interface DisposeResponse {
        /**
         * 请求成功后处理响应
         *
         * @param response 请求响应
         * @return 默认响应转为字符串
         */
        String dispose(CloseableHttpResponse response);
    }

    public interface ParamsBuilder {
        /**
         * 构建请求参数
         *
         * @return 参数实体
         * @throws IOException 构建参数异常
         */
        HttpEntity build() throws IOException;
    }

}
