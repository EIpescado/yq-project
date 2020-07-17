package pers.yurwisher.wechat.miniapp.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.wechat.miniapp.SubscribeMessageService;
import pers.yurwisher.wechat.miniapp.message.SubscribeMessage;
import pers.yurwisher.wechat.mp.api.impl.TemplateServiceImpl;
import pers.yurwisher.wechat.core.CoreService;

/**
 * @author yq
 * @date 2020/07/08 10:21
 * @description 订阅消息推送
 * @since V1.0.0
 */
public class SubscribeMessageServiceImpl implements SubscribeMessageService {

    private final static Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);
    private CoreService coreService;

    public SubscribeMessageServiceImpl(CoreService coreService) {
        this.coreService = coreService;
    }

    @Override
    public void send(SubscribeMessage subscribeMessage) {
        logger.info("推送小程序订阅消息: [{}]",subscribeMessage.getTemplate_id());
        String responseStr = coreService.getHttpRequest().postWithToken(SEND_TEMPLATE_MESSAGE_URL,coreService.getConfigRepository().getAccessToken(), JSON.toJSONString(subscribeMessage));
        JSONObject json = coreService.judgeValidParseJSON(responseStr);
        logger.info("推送小程序订阅消息成功,消息ID:[{}]", json.getString("msgid"));
    }
}
