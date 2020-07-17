package pers.yurwisher.wechat.miniapp;

/**
 * @author yq
 * @date 2020/07/16 10:43
 * @description 小程序接口
 * @since V1.0.0
 */
public interface MiniappService {

    /**
     * 模版消息service
     * @return TemplateService
     */
    SubscribeMessageService getSubscribeMessageService();

}
