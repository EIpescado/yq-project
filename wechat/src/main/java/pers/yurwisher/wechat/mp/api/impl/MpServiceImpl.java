package pers.yurwisher.wechat.mp.api.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.wechat.common.constants.WeChatConstant;
import pers.yurwisher.wechat.common.enums.MpErrorEnum;
import pers.yurwisher.wechat.common.enums.WxType;
import pers.yurwisher.wechat.common.utils.Utils;
import pers.yurwisher.wechat.common.utils.http.HttpRequest;
import pers.yurwisher.wechat.core.PushConfigRepository;
import pers.yurwisher.wechat.exception.WeChatException;
import pers.yurwisher.wechat.mp.api.KefuService;
import pers.yurwisher.wechat.mp.api.MaterialService;
import pers.yurwisher.wechat.mp.api.MpService;
import pers.yurwisher.wechat.mp.api.TemplateService;
import pers.yurwisher.wechat.mp.api.WxMenuService;
import pers.yurwisher.wechat.core.CoreService;
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
public class MpServiceImpl implements MpService {

    private static final Logger logger = LoggerFactory.getLogger(MpServiceImpl.class);

    private TemplateService templateService = new TemplateServiceImpl(this);
    private WxMenuService wxMenuService = new WxMenuServiceImpl(this);
    private KefuService kefuService = new KefuServiceImpl(this);
    private MaterialService materialService = new MaterialServiceImpl(this);
    private CoreService coreService;

    public MpServiceImpl(CoreService coreService) {
        this.coreService = coreService;
    }

    @Override
    public TemplateService getTemplateService() {
        return templateService;
    }


    @Override
    public HttpRequest getHttpRequest() {
        return coreService.getHttpRequest();
    }

    @Override
    public PushConfigRepository getConfigRepository() {
        return coreService.getConfigRepository();
    }

    @Override
    public JSONObject judgeValidParseJSON(String responseStr, WxType type) throws WeChatException{
        if (Utils.isNotEmpty(responseStr)) {
            JSONObject json = JSON.parseObject(responseStr);
            String errorCode = json.getString(WeChatConstant.ERROR_CODE_KEY);
            //接口返回包含错误码,且不为成功码0
            if (Utils.isNotEmpty(errorCode) && !WeChatConstant.SUCCESS_CODE.equals(errorCode)) {
                /*
                   * 发生以下情况时尝试刷新access_token
                   * 40001 获取access_token时AppSecret错误，或者access_token无效
                   * 42001 access_token超时
                   * 40014 不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口
                    */
                if (Integer.toString(MpErrorEnum.CODE_42001.getCode()).equals(errorCode)
                        || Integer.toString(MpErrorEnum.CODE_40001.getCode()).equals(errorCode)
                        || Integer.toString(MpErrorEnum.CODE_40014.getCode()).equals(errorCode)) {
                    //作废token 下次请求自动刷新
                    logger.info("token已无效,自动刷新token");
                    coreService.getConfigRepository().expireAccessToken();
                }
                logger.error("错误码:{},详情:{}", errorCode, json.getString(WeChatConstant.ERROR_MSG_KEY));
                /**优先返回中文信息,微信接口返回为英文提示*/
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
    public WxMenuService getWxMenuService() {
        return wxMenuService;
    }

    @Override
    public List<String> getCallbackIP() throws WeChatException{
        String responseStr = coreService.getHttpRequest().getWithToken(GET_CALL_BACK_IP_URL ,coreService.getConfigRepository().getAccessToken());
        JSONObject json =  judgeValidParseJSON(responseStr, WxType.MP);
        JSONArray array = json.getJSONArray("ip_list");
        if(Utils.isNotEmpty(array)){
            return array.toJavaList(String.class);
        }
        return null;
    }

    @Override
    public KefuService getKefuService() {
        return kefuService;
    }

    @Override
    public WxMpUserList getUserList(String nextOpenid) throws WeChatException {
        String responseStr = coreService.getHttpRequest().getWithToken(GET_USER_LIST_URL ,coreService.getConfigRepository().getAccessToken(),"&next_openid=" + nextOpenid);
        JSONObject json =  judgeValidParseJSON(responseStr, WxType.MP);
        return json.toJavaObject(WxMpUserList.class);
    }

    @Override
    public WxMpUser getUserInfo(String openId) throws WeChatException {
        String responseStr = coreService.getHttpRequest().getWithToken(GET_USER_INFO_URL ,coreService.getConfigRepository().getAccessToken(),"&lang=zh_CN&openid=" + openId);
        JSONObject json =  judgeValidParseJSON(responseStr, WxType.MP);
        return json.toJavaObject(WxMpUser.class);
    }

    @Override
    public List<WxMpUser> batchGetUserInfo(List<String> openIds) throws WeChatException {
        WxMpUserQuery query = new WxMpUserQuery(openIds);
        String responseStr = coreService.getHttpRequest().postWithToken(BATCH_GET_USER_INFO_URL ,coreService.getConfigRepository().getAccessToken(),JSON.toJSONString(query));
        JSONObject json =  judgeValidParseJSON(responseStr, WxType.MP);
        JSONArray array = json.getJSONArray("user_info_list");
        return array.toJavaList(WxMpUser.class);
    }

    @Override
    public MaterialService getMaterialService() {
        return materialService;
    }

}
