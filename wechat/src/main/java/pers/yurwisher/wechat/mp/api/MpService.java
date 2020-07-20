package pers.yurwisher.wechat.mp.api;

import com.alibaba.fastjson.JSONObject;
import pers.yurwisher.wechat.common.enums.WxType;
import pers.yurwisher.wechat.common.utils.http.HttpRequest;
import pers.yurwisher.wechat.core.PushConfigRepository;
import pers.yurwisher.wechat.exception.WeChatException;
import pers.yurwisher.wechat.mp.bean.WxMpUser;
import pers.yurwisher.wechat.mp.bean.WxMpUserList;
import pers.yurwisher.wechat.mp.bean.WxMpUserQuery;

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
     * 获取帐号的关注者列表
     */
    String GET_USER_LIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get";

    /**
     * 获取用户基本信息
     */
    String GET_USER_INFO_URL= "https://api.weixin.qq.com/cgi-bin/user/info";

    /**
     * 批量获取用户信息
     */
    String BATCH_GET_USER_INFO_URL= "https://api.weixin.qq.com/cgi-bin/user/info/batchget";

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
     * 微信菜单service
     * @return WxMenuService
     */
    WxMenuService getWxMenuService();

    /**
     * 客服service
     * @return KefuService
     */
    KefuService getKefuService();

    /**
     * 验证及转化 接口返回json
     * @param responseStr 请求返回的字符串
     * @param type 微信程序类型
     * @return JSONObject
     * @throws WeChatException
     */
    JSONObject judgeValidParseJSON(String responseStr, WxType type) throws WeChatException;

    /**
     * 获取微信服务器IP地址
     * @return 获得微信服务器IP地址列表或者IP网段信息
     */
    List<String> getCallbackIP() throws WeChatException;

    /**
     * 获取公众号关注者用户列表
     * @param nextOpenid 可选，第一个拉取的OPENID，null为从头开始拉取
     * @throws WeChatException
     */
    WxMpUserList getUserList(String nextOpenid) throws WeChatException;

    /**
     * 获取关注者信息
     * @param openId 关注者openId
     * @return  关注者信息
     * @throws WeChatException
     */
    WxMpUser getUserInfo(String openId) throws WeChatException;

    /**
     *  批量获取关注者信息 最多支持一次拉取100条。
     * @param openIds
     * @return  WxMpUser集合
     * @throws WeChatException
     */
    List<WxMpUser> batchGetUserInfo(List<String> openIds) throws WeChatException;
}
