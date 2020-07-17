package pers.yurwisher.wechat.mp.api;

import com.alibaba.fastjson.JSONObject;
import pers.yurwisher.wechat.common.enums.WxType;
import pers.yurwisher.wechat.common.utils.http.HttpRequest;
import pers.yurwisher.wechat.core.PushConfigRepository;
import pers.yurwisher.wechat.exception.WeChatException;

import java.util.List;

/**
 * @author yq
 * @date 2018/07/26 14:52
 * @description 公众号service
 * @since V1.0.0
 */
public interface MpService {

    /**
     * 获取微信服务器IP地址
     */
    String GET_CALL_BACK_IP_URL = "https://api.weixin.qq.com/cgi-bin/getcallbackip";

    /**
     * 模版消息service
     * @return TemplateService
     */
    TemplateService getTemplateService();

    /**
     * 请求 service
     * @return HttpRequest
     */
    HttpRequest getHttpRequest();

    /**
     * 微信配置仓库
     * @return MpConfigRepository
     */
    PushConfigRepository getConfigRepository();

    /**
     * 验证及转化 接口返回json
     * @param responseStr 请求返回的字符串
     * @param type 微信程序类型
     * @return JSONObject
     * @throws WeChatException
     */
    JSONObject judgeValidParseJSON(String responseStr, WxType type) throws WeChatException;


    /**
     * 微信菜单service
     * @return WxMenuService
     */
    WxMenuService getWxMenuService();

    /**
     * 获取微信服务器IP地址
     * @return 获得微信服务器IP地址列表或者IP网段信息
     */
    List<String> getCallbackIP();

    /**
     * 客服service
     * @return KefuService
     */
    KefuService getKefuService();
}
