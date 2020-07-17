package pers.yurwisher.wechat.miniapp;

import pers.yurwisher.wechat.miniapp.message.SubscribeMessage;

/**
 * @author yq
 * @date 2020/07/08 10:02
 * @description 小程序订阅消息
 * @since V1.0.0
 */
public interface SubscribeMessageService {

    /**
     * 发送模版消息 接口地址 post
     */
    String SEND_TEMPLATE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send";

    /**
     * 发送订阅消息
     * @param subscribeMessage 订阅消息
     */
    void send(SubscribeMessage subscribeMessage);
}
