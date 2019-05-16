package pers.yurwisher.grabber.singlewindow;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.grabber.GrabException;
import pers.yurwisher.grabber.HttpClientHelper;
import pers.yurwisher.grabber.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author yq
 * @date 2019/05/06 15:09
 * @description 海关单一窗口
 * @since V1.0.0
 */
public class SingleWindowGrabber {

    private static final Logger logger = LoggerFactory.getLogger(SingleWindowGrabber.class);

    /**
     * 文件地址
     */
    private static final String PDF_URL = "http://sz.singlewindow.cn/dyck/swProxy/decserver/entries/ftl/1/0/0/%1$s.pdf";
    private static final String PDF_ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
    private static final String PDF_ACCEPT_LANGUAGE = "zh-CN,zh;q=0.9,en;q=0.8";
    private static final String PDF_HOST = "sz.singlewindow.cn";
    private static final String PDF_UPGRADE_INSECURE_REQUESTS = "1";
    private static final String PDF_REFERER = "http://sz.singlewindow.cn/dyck/swProxy/decserver/sw/dec/cusQueryZh?ngBasePath=http%3A%2F%2Fsz.singlewindow.cn%3A80%2Fdyck%2FswProxy%2Fdecserver%2F";

    private static final String QUERY_PATH = "http://sz.singlewindow.cn/dyck/swProxy/decserver/sw/dec/merge/cusQuery?";

    private static final String QUERY_ACCEPT = "application/json, text/javascript, */*; q=0.01";

    private static final String PDF_HEADER = "application/pdf;charset=UTF-8";

    private HttpClientHelper httpClientHelper;

    public SingleWindowGrabber(HttpClientHelper httpClientHelper) {
        this.httpClientHelper = httpClientHelper;
    }

    /***
     * 查询报关单
     * @param singleWindowQueryParams 查询参数
     * @param cookie 查询参数 单一窗口校验需要,可能过期
     * @return 列表
     */
    public SingleWindowsDeclarationResult query(SingleWindowQueryParams singleWindowQueryParams,String cookie) {
        String result = httpClientHelper.sendGet(QUERY_PATH + singleWindowQueryParams.build(), createHeadersForQuery(cookie));
        if (Utils.isNotEmpty(result)) {
            if(result.contains("<!DOCTYPE html>")){
                throw new GrabException("cookie have expired,Please reset");
            }
            JSONObject json = JSON.parseObject(result);
            return json.toJavaObject(SingleWindowsDeclarationResult.class);
        }else {
            throw new GrabException("single window interface response exception,Try again later");
        }
    }

    public void downloadPdf(String no, String cookie, OutputStream outputStream){
        downloadPdf(no, cookie, () -> outputStream );
    }

    /**
     * 下载pdf
     * @param no   单一窗口统一编号
     * @param dicPath pdf保存文件夹路径
     * @param fileName 文件名称
     * @param cookie 单一窗口校验需要,可能过期
     */
    public void downloadPdf(String no, String dicPath, String fileName ,String cookie) {
        downloadPdf(no, cookie, () -> {
            File file = new File(dicPath);
            if(!file.exists()){
                file.mkdir();
            }
            return new FileOutputStream(dicPath + "/" + fileName);
        } );
    }

    private void downloadPdf(String no, String cookie,OutputStreamCreater outputStreamCreater){
        String url = String.format(PDF_URL, no);
        CloseableHttpResponse response;
        HttpEntity entity;
        // 构建请求地址 创建httpGet.
        HttpGet httpget = null;
        OutputStream outputStream = null;
        try {
            httpget = new HttpGet(url);
            //设置请求参数
            httpget.setHeaders(createHeaders(cookie));
            // 执行get请求.
            response = httpClientHelper.getDefaultClient().execute(httpget);
            final StatusLine statusLine = response.getStatusLine();
            //响应码
            int responseCode = statusLine.getStatusCode();
            // 获取响应实体
            entity = response.getEntity();
            //响应成功
            if (HttpStatus.SC_OK == responseCode) {
                //header为pdf 表示可以直接下载
                if(PDF_HEADER.equals(entity.getContentType().getValue())){
                    outputStream = outputStreamCreater.create();
                    entity.writeTo(outputStream);
                }else {
                    throw new GrabException("cookie have expired,Please reset");
                }
            }else {
                throw new GrabException(no + " download fail");
            }
        } catch (IOException e) {
            logger.error("{} download error", no,e);
            throw new GrabException(no + " download fail");
        } finally {
            if (httpget != null) {
                //释放连接
                httpget.releaseConnection();
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    logger.info("close outputStream fail");
                }
            }
        }
    }

    /**
     * 下载PDF的请求头
     *
     * @param cookie cookie
     */
    private BasicHeader[] createHeaders(String cookie) {
        return new BasicHeader[]{
                new BasicHeader("Accept", PDF_ACCEPT),
                new BasicHeader("Referer", PDF_REFERER),
                new BasicHeader("Accept-Language", PDF_ACCEPT_LANGUAGE),
                new BasicHeader("Cookie", cookie),
                new BasicHeader("Host", PDF_HOST),
                new BasicHeader("Upgrade-Insecure-Requests", PDF_UPGRADE_INSECURE_REQUESTS)
        };
    }

    /**
     * 查询报关单的请求头
     *
     * @param cookie cookie
     */
    private BasicHeader[] createHeadersForQuery(String cookie) {
        return new BasicHeader[]{
                new BasicHeader("Accept", QUERY_ACCEPT),
                new BasicHeader("Referer", PDF_REFERER),
                new BasicHeader("Accept-Language", PDF_ACCEPT_LANGUAGE),
                new BasicHeader("Cookie", cookie),
                new BasicHeader("Host", PDF_HOST),
                new BasicHeader("Upgrade-Insecure-Requests", PDF_UPGRADE_INSECURE_REQUESTS),
                new BasicHeader("Content-Type", "application/json")
        };
    }
}
