package pers.yurwisher.wechat.core.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.wechat.common.base.WxAccessToken;
import pers.yurwisher.wechat.common.constants.WeChatConstant;
import pers.yurwisher.wechat.common.enums.MpErrorEnum;
import pers.yurwisher.wechat.common.utils.Utils;
import pers.yurwisher.wechat.common.utils.crypto.MsgCrypt;
import pers.yurwisher.wechat.common.utils.http.HttpRequest;
import pers.yurwisher.wechat.common.utils.http.apache.HttpRequestApacheImpl;
import pers.yurwisher.wechat.core.PushConfigRepository;
import pers.yurwisher.wechat.exception.WeChatException;
import pers.yurwisher.wechat.core.CoreService;
import pers.yurwisher.wechat.core.bean.JsCode2Session;

/**
 * @author yq
 * @date 2018/12/17 13:46
 * @description 核心
 * @since V1.0.0
 */
public class CoreServiceImpl implements CoreService {

    private static final Logger logger = LoggerFactory.getLogger(CoreServiceImpl.class);

    private HttpRequest httpRequest ;
    private PushConfigRepository configRepository;
    private MsgCrypt msgCrypt;

    public CoreServiceImpl(HttpRequest httpRequest,PushConfigRepository configRepository) {
        this.httpRequest = httpRequest;
        this.configRepository = configRepository;
    }
    public CoreServiceImpl(PushConfigRepository configRepository) {
        this(HttpRequestApacheImpl.INSTANCE, configRepository);
    }

    @Override
    public JsCode2Session getJsCode2Session(String code) {
        String url = String.format(JS_CODE_TO_SESSION_URL,configRepository.getAppId(),configRepository.getSecret(),code);
        JSONObject jsonObject = judgeValidParseJSON(httpRequest.get(url));
        return jsonObject.toJavaObject(JsCode2Session.class);
    }

    @Override
    public JSONObject judgeValidParseJSON(String responseStr) throws WeChatException {
        if (Utils.isNotEmpty(responseStr)) {
            JSONObject json = JSON.parseObject(responseStr);
            String errorCode = json.getString(WeChatConstant.ERROR_CODE_KEY);
            //接口返回包含错误码,且不为成功码0
            if (Utils.isNotEmpty(errorCode) && !WeChatConstant.SUCCESS_CODE.equals(errorCode)) {
                logger.error("错误码:{},详情:{}", errorCode, json.getString(WeChatConstant.ERROR_MSG_KEY));
                /**优先返回中文信息,微信接口返回为英文提示*/
                /*
                 * 发生以下情况时尝试刷新access_token
                 * 40001 获取access_token时AppSecret错误，或者access_token无效
                 * 42001 access_token超时
                 * 40014 不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口
                 */
                Integer errorCodeInt = Integer.parseInt(errorCode);
                if (MpErrorEnum.CODE_42001.eq(errorCodeInt) || MpErrorEnum.CODE_40001.eq(errorCodeInt) || MpErrorEnum.CODE_40014.eq(errorCodeInt)) {
                    //作废token 下次请求自动刷新
                    configRepository.expireAccessToken();
                }
                String description = MpErrorEnum.getDescriptionByCode(errorCode);
                description = Utils.isNotEmpty(description) ? description : json.getString(WeChatConstant.ERROR_MSG_KEY);
                throw new WeChatException(description);
            }
            return json;
        } else {
            logger.error("请求异常,无返回结果");
            throw new WeChatException("接口请求失败!");
        }
    }

    @Override
    public WxAccessToken getAccessToken() {
        String responseStr = httpRequest.get(String.format(ACCESS_TOKEN_URL,configRepository.getAppId(),configRepository.getSecret()));
        JSONObject json = judgeValidParseJSON(responseStr);
        WxAccessToken accessToken = json.toJavaObject(WxAccessToken.class);
        logger.info("[{}] 最新token:[{}]",configRepository.getAppId(), accessToken.getAccessToken());
        return accessToken;
    }

    @Override
    public HttpRequest getHttpRequest() {
        return httpRequest;
    }

    @Override
    public PushConfigRepository getConfigRepository() {
        return configRepository;
    }

    @Override
    public MsgCrypt getMsgCrypt() {
        if(msgCrypt == null){
            msgCrypt = new MsgCrypt(configRepository.getToken(),configRepository.getAesKey(),configRepository.getAppId());
        }
        return msgCrypt;
    }

}
