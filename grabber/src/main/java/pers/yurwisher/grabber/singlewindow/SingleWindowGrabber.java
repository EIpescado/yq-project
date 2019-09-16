package pers.yurwisher.grabber.singlewindow;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
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
     * 报关单文件地址
     */
    private static final String PDF_URL = "http://sz.singlewindow.cn/dyck/swProxy/decserver/entries/ftl/1/0/0/%1$s.pdf";
    private static final String PDF_ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
    private static final String PDF_ACCEPT_LANGUAGE = "zh-CN,zh;q=0.9,en;q=0.8";
    private static final String PDF_HOST = "sz.singlewindow.cn";
    private static final String PDF_UPGRADE_INSECURE_REQUESTS = "1";
    private static final String PDF_REFERER = "http://sz.singlewindow.cn/dyck/swProxy/decserver/sw/dec/cusQueryZh?ngBasePath=http%3A%2F%2Fsz.singlewindow.cn%3A80%2Fdyck%2FswProxy%2Fdecserver%2F";
    /**
     * 报关单查询地址
     */
    private static final String QUERY_PATH = "http://sz.singlewindow.cn/dyck/swProxy/decserver/sw/dec/merge/cusQuery?";
    private static final String QUERY_ACCEPT = "application/json, text/javascript, */*; q=0.01";
    private static final String PDF_HEADER = "application/pdf;charset=UTF-8";
    private static final String LOGIN_HTML_START = "<!DOCTYPE html>";

    /***
     * 查询报关单
     * @param singleWindowQueryParams 查询参数
     * @param cookie 查询参数 单一窗口校验需要,可能过期
     * @return 列表
     */
    public SingleWindowsDeclarationResult query(SingleWindowQueryParams singleWindowQueryParams, String cookie) {
        String result = HttpClientHelper.getInstance().sendGet(QUERY_PATH + singleWindowQueryParams.build(), createHeadersForQuery(cookie));
        if (Utils.isNotEmpty(result)) {
            if (result.contains(LOGIN_HTML_START)) {
                throw new GrabException("cookie have expired,Please reset");
            }
            JSONObject json = JSON.parseObject(result);
            return json.toJavaObject(SingleWindowsDeclarationResult.class);
        } else {
            throw new GrabException("single window interface response exception,Try again later");
        }
    }

    public void downloadPdf(String no, String cookie, OutputStream outputStream) {
        downloadPdf(no, cookie, () -> outputStream);
    }

    /**
     * 下载pdf
     *
     * @param no       单一窗口统一编号
     * @param dicPath  pdf保存文件夹路径
     * @param fileName 文件名称
     * @param cookie   单一窗口校验需要,可能过期
     */
    public void downloadPdf(String no, String dicPath, String fileName, String cookie) {
        downloadPdf(no, cookie, () -> {
            File file = new File(dicPath);
            Utils.mkDir(file);
            return new FileOutputStream(dicPath + File.separator + fileName);
        });
    }

    private void downloadPdf(String no, String cookie, OutputStreamCreater outputStreamCreater) {
        String url = String.format(PDF_URL, no);
        HttpClientHelper.getInstance().downloadFile(url, response -> {
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            //header为pdf 表示可以直接下载
            if (PDF_HEADER.equals(entity.getContentType().getValue())) {
                OutputStream outputStream = null;
                try {
                    outputStream = outputStreamCreater.create();
                    entity.writeTo(outputStream);
                } catch (IOException e) {
                    logger.error("输出流写出异常", e);
                    throw new GrabException("entity write to outputStream error");
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            logger.error("关闭输出流异常", e);
                        }
                    }
                }
            } else {
                throw new GrabException("cookie have expired,Please reset");
            }
            return null;
        }, createHeaders(cookie));
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
