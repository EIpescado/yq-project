package pers.yurwisher.wechat.core;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.wechat.common.utils.Utils;
import pers.yurwisher.wechat.common.utils.crypto.SHA1;
import pers.yurwisher.wechat.exception.AesException;
import pers.yurwisher.wechat.mp.api.MpService;
import pers.yurwisher.wechat.mp.api.WxMessageRouter;
import pers.yurwisher.wechat.mp.in.WxMpXmlMessage;
import pers.yurwisher.wechat.mp.message.WxMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author yq
 * @date 2018/12/13 14:28
 * @description MP servlet
 * @since V1.0.0
 */
public class WeChatMpServlet extends HttpServlet {
    private static final long serialVersionUID = -982845246865669239L;
    private static final Logger logger = LoggerFactory.getLogger(WeChatMpServlet.class);

    private static final String EMPTY = "";

    private WxMessageRouter wxMessageRouter;
    private MpService mpService;

    public WeChatMpServlet(WxMessageRouter wxMessageRouter, MpService mpService) {
        this.wxMessageRouter = wxMessageRouter;
        this.mpService = mpService;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        //微信加密签名
        String signature = request.getParameter("signature");
        //时间戳
        String timestamp = request.getParameter("timestamp");
        //随机数
        String nonce = request.getParameter("nonce");
        //随机字符串
        String echostr = request.getParameter("echostr");
        //加密类型
        String encryptType = request.getParameter("encrypt_type");

        logger.debug("signature:{},timestamp:{},nonce:{},echostr:{},encryptType:{}", signature, timestamp, nonce, echostr, encryptType);

        //字段提取成功
        if (Utils.isNotEmpty(signature) && Utils.isNotEmpty(nonce)) {
            //token ,timestamp,nonce 字典序排序拼接成字符串,并通过sha1加密得到字符串
            String hashcode = SHA1.gen(mpService.getMpConfigRepository().getToken(), timestamp, nonce);
            //确定该数据是不是来源于微信后台
            boolean isFromWx = hashcode.equals(signature);
            logger.debug("数据是否来源于微信后台:{}", isFromWx);
            if (isFromWx && Utils.isNotEmpty(echostr)) {
                //来源于微信 只是一个验证请求
                response.getWriter().println(echostr);
                return;
            }
        }

        //消息主体
        String postData = IOUtils.toString(request.getInputStream(), Charset.forName("UTF-8"));
        logger.debug("postData:{}", postData);
        if(Utils.isEmpty(postData)){
            response.getWriter().println(EMPTY);
            return;
        }
        //明文
        if (Utils.isEmpty(encryptType)) {
            //映射成对象
            WxMpXmlMessage xmlMessage = WxMpXmlMessage.fromXml(postData);
            logger.info("消息类型:{},消息ID:{},用户openId:{}", xmlMessage.getMsgType(), xmlMessage.getMsgId(), xmlMessage.getFromUser());
            //消息经过路由后,返回处理消息
            WxMessage wxMessage = wxMessageRouter.route(xmlMessage);
            if (wxMessage != null) {
                response.getWriter().write(wxMessage.toXml());
                return;
            }
        } else if ("AES".equalsIgnoreCase(encryptType)) {
            //加密类型,为aes
            //消息签名，用于验证消息体的正确性
            String msgSignature = request.getParameter("msg_signature");
            logger.debug("msg_signature:{}", msgSignature);
            //解密消息报文
            try {
                //消息解密后结果
                postData = mpService.getMsgCrypt().decryptMsg(msgSignature, timestamp, nonce, postData);
                //映射成对象
                WxMpXmlMessage xmlMessage = WxMpXmlMessage.fromXml(postData);
                logger.info("消息类型:{},消息ID:{},用户openId:{}", xmlMessage.getMsgType(), xmlMessage.getMsgId(), xmlMessage.getFromUser());
                //消息经过路由后,返回处理消息
                WxMessage wxMessage = wxMessageRouter.route(xmlMessage);
                if (wxMessage != null) {
                    //加密
                    String resultXml = mpService.getMsgCrypt().generateEncryptedXml(wxMessage.toXml());
                    logger.debug("加密后的消息报文:{}", resultXml);
                    response.getWriter().write(resultXml);
                    return;
                }
            } catch (AesException e) {
                logger.error("加解密工具类异常:{}", e.getMessage());
            }
        }
        response.getWriter().println(EMPTY);
    }
}
