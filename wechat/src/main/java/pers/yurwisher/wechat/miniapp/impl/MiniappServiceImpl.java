package pers.yurwisher.wechat.miniapp.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.wechat.miniapp.MiniappService;
import pers.yurwisher.wechat.miniapp.SubscribeMessageService;
import pers.yurwisher.wechat.core.CoreService;

/**
 * @author yq
 * @date 2020/07/16 10:44
 * @description 小程序接口
 * @since V1.0.0
 */
public class MiniappServiceImpl implements MiniappService {

    private static final Logger logger = LoggerFactory.getLogger(MiniappServiceImpl.class);

    private CoreService coreService;
    private SubscribeMessageService subscribeMessageService;

    public MiniappServiceImpl(CoreService coreService) {
        this.coreService = coreService;
        this.subscribeMessageService = new SubscribeMessageServiceImpl(coreService);
    }

    @Override
    public SubscribeMessageService getSubscribeMessageService() {
        return subscribeMessageService;
    }

}
