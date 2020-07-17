package pers.yurwisher.wechat.core;

import com.alibaba.fastjson.JSONObject;
import pers.yurwisher.wechat.common.base.WxAccessToken;
import pers.yurwisher.wechat.common.utils.crypto.MsgCrypt;
import pers.yurwisher.wechat.common.utils.http.HttpRequest;
import pers.yurwisher.wechat.core.bean.JsCode2Session;
import pers.yurwisher.wechat.exception.WeChatException;

/**
 * @author yq
 * @date 2018/12/17 13:46
 * @description 核心
 * @since V1.0.0
 */
public interface CoreService {

   /**
    * 微信授权登录接口地址
    */
   String JS_CODE_TO_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%1$s&secret=%2$s&js_code=%3$s&grant_type=authorization_code";

   /**
    * 获取token地址
    */
   String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%1$s&secret=%2$s";

   /**
    * 获取登录凭证校验结果
    * @param code 登录时获取的 code
    * @return  登录凭证校验结果
    */
   JsCode2Session getJsCode2Session(String code);

   /**
    * 校验接口返回并转为json
    * @param responseStr 微信接口返回
    * @return JSONObject
    * @throws WeChatException
    */
   JSONObject judgeValidParseJSON(String responseStr) throws WeChatException;

   /**
    * 获取token
    * @return token
    */
   WxAccessToken getAccessToken();

   /**
    * http请求工具
    * @return
    */
   HttpRequest getHttpRequest();

   /**
    * 获取相关微信配置
    * @return
    */
   PushConfigRepository getConfigRepository();

   /**
    * 提供接收和推送给公众平台消息的加解密接口
    * @return MsgCrypt
    */
   MsgCrypt getMsgCrypt();
}
