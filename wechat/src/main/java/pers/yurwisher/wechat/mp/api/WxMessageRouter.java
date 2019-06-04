package pers.yurwisher.wechat.mp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.wechat.common.utils.Utils;
import pers.yurwisher.wechat.mp.api.impl.DefaultWxMessageDuplicateChecker;
import pers.yurwisher.wechat.mp.in.WxMpXmlMessage;
import pers.yurwisher.wechat.mp.message.WxMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 消息路由
 * @author yq
 */
public class WxMessageRouter {


    private final static Logger logger = LoggerFactory.getLogger(WxMessageRouter.class);
    /**
     * 默认异步执行线程数
     */
    private static final int DEFAULT_THREAD_POOL_SIZE = 15;

    /**
     * 消息验重
     */
    private WxMessageDuplicateChecker wxMessageDuplicateChecker;

    /**
     * 消息验证规则
     */
    private final List<WxMessageRouterRule> rules = new ArrayList<>();

    /**
     * 异步执行线程
     */
    private ExecutorService executorService;

    /**
     * 默认路由
     */
    public WxMessageRouter() {
        this.wxMessageDuplicateChecker = new DefaultWxMessageDuplicateChecker() ;
        this.executorService = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);
    }

    /**
     * 默认路由
     */
    public WxMessageRouter(WxMessageDuplicateChecker wxMessageDuplicateChecker) {
        this.wxMessageDuplicateChecker = wxMessageDuplicateChecker ;
    }


    /**
     * 自定义路由
     * @param wxMessageDuplicateChecker 消息判重
     * @param executorService 执行器材
     */
    public WxMessageRouter(WxMessageDuplicateChecker wxMessageDuplicateChecker, ExecutorService executorService) {
        this.wxMessageDuplicateChecker = wxMessageDuplicateChecker ;
        this.executorService = executorService;
    }

    /**
     * 消息路由到对应节点进行处理
     * @param xmlMessage 消息类型
     * @return 回复的消息xml 字符串
     */
    public WxMessage route(final WxMpXmlMessage xmlMessage) {
        //重复消息
        if(isMsgDuplicated(xmlMessage)){
            return null;
        }
        //获取匹配的规则
        List<WxMessageRouterRule> matchRules = new ArrayList<>(this.rules.size());
        for(WxMessageRouterRule rule : this.rules){
            if (rule != null && rule.test(xmlMessage)) {
                matchRules.add(rule);
                //本次规则是否允许继续往下匹配
                if (!rule.isReEnter()) {
                    break;
                }
            }
        }
        logger.info("消息:{},匹配的规则:{}项",xmlMessage.getMsgId(), Utils.size(matchRules));
        int matchSize =  Utils.size(matchRules) ;
        if(matchSize == 0){
            return null;
        }

        // 返回最后一个非异步的rule的执行结果
        WxMessage res = null ;
        final List<Future<WxMessage>> futures = new ArrayList<>(matchSize);
        //匹配规则执行
        for(WxMessageRouterRule rule: matchRules){
            //异步执行
            if(rule.isAsync()){
                futures.add(
                    //提交执行,返回异步执行的操作,即Future
                    this.executorService.submit(()->
                        rule.service(xmlMessage)
                    )
                );
            }else {
                res = rule.service(xmlMessage);
            }
        }
        if (futures.size() > 0) {
            logger.info("消息:{},异步执行的操作:{}" ,xmlMessage.getMsgId(),futures.size());
            this.executorService.submit(()->{
                for (Future<WxMessage> future : futures) {
                    //超时时间获取
                    try {
                        //执行 4秒超时
                        future.get(4, TimeUnit.SECONDS);
                    } catch (InterruptedException |ExecutionException | TimeoutException e) {
                        logger.error("Error happened when wait task finish", e);
                    }
                }
            });
        }
        return res;
    }

    /**
     * 消息判重
     * @param wxMessage 消息
     * @return boolean
     */
    public boolean isMsgDuplicated(WxMpXmlMessage wxMessage) {
        StringBuilder messageId = new StringBuilder();
        if (wxMessage.getMsgId() == null) {
            messageId.append(wxMessage.getCreateTime())
                    .append("-").append(wxMessage.getFromUser())
                    .append("-").append(Utils.null2EmptyWithTrimNew(wxMessage.getEventKey()))
                    .append("-").append(Utils.null2EmptyWithTrimNew(wxMessage.getEvent()));
        } else {
            messageId.append(wxMessage.getMsgId())
                    .append("-").append(wxMessage.getCreateTime())
                    .append("-").append(wxMessage.getFromUser());
        }
        boolean isDuplicate = this.wxMessageDuplicateChecker.isDuplicate(messageId.toString());
        if(isDuplicate){
            logger.info("msgId:{},重复",wxMessage.getMsgId());
        }
        return isDuplicate;
    }


    List<WxMessageRouterRule> getRules() {
        return this.rules;
    }

    /**
     * 开始一个新的Route规则
     */
    public WxMessageRouterRule rule() {
        return new WxMessageRouterRule(this);
    }

}
