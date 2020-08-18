package pers.yurwisher.wechat.mp.api.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pers.yurwisher.wechat.common.enums.WxType;
import pers.yurwisher.wechat.common.utils.Utils;
import pers.yurwisher.wechat.mp.api.KefuService;
import pers.yurwisher.wechat.mp.api.MpService;
import pers.yurwisher.wechat.mp.kf.KefuAccount;
import pers.yurwisher.wechat.mp.kf.KefuMessage;
import pers.yurwisher.wechat.mp.kf.KefuMsgRecord;
import pers.yurwisher.wechat.mp.kf.KefuMsgRequest;
import pers.yurwisher.wechat.mp.kf.KefuSession;
import pers.yurwisher.wechat.mp.kf.KefuSessionWaitCase;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author yq
 * @date 2018/07/30 17:34
 * @description 客服帐号管理 //todo 未校验
 * @since V1.0.0
 */
public class KefuServiceImpl implements KefuService {

    private MpService mpService;

    public KefuServiceImpl(MpService mpService) {
        this.mpService = mpService;
    }

    @Override
    public void add(KefuAccount account) {
        if(account != null){
            String params = account.toJSON();
            String responseStr = mpService.getHttpRequest().postWithToken(ADD_POST_URL,mpService.getConfigRepository().getAccessToken(),params);
            mpService.judgeValidParseJSON(responseStr, WxType.MP);
        }
    }

    @Override
    public void update(KefuAccount account) {
        if(account != null){
            String params = account.toJSON();
            String responseStr = mpService.getHttpRequest().postWithToken(UPDATE_POST_URL,mpService.getConfigRepository().getAccessToken(),params);
            mpService.judgeValidParseJSON(responseStr, WxType.MP);
        }
    }

    @Override
    public void del(String kfAccount) {
        if(Utils.isNotEmpty(kfAccount)){
            String responseStr = mpService.getHttpRequest().get(String.format(DELETE_GET_URL,mpService.getConfigRepository().getAccessToken(),kfAccount));
            mpService.judgeValidParseJSON(responseStr, WxType.MP);
        }
    }

    @Override
    public void uploadHeadImg(String kfAccount, InputStream imgFile) {
        if(Utils.isNotEmpty(kfAccount)){
            String responseStr = mpService.getHttpRequest().uploadFile(String.format(UPLOAD_HEAD_IMG_POST_URL,mpService.getConfigRepository().getAccessToken(),kfAccount),imgFile,"media");
            mpService.judgeValidParseJSON(responseStr, WxType.MP);
        }
    }

    @Override
    public List<KefuAccount> getKefuList(boolean getAll) {
        String url = getAll ? GET_KEFU_LIST_GET_URL : GET_ONLINE_KF_LIST_GET_URL ;
        String responseStr = mpService.getHttpRequest().getWithToken(url,mpService.getConfigRepository().getAccessToken());
        JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
        String key = getAll ? "kf_list" : "kf_online_list";
        JSONArray array = json.getJSONArray(key);
        if(Utils.isNotEmpty(array)){
            return array.toJavaList(KefuAccount.class);
        }
        return null;
    }

    @Override
    public void inviteWorker(KefuAccount account) {
        if(account != null){
            String params = account.toJSON();
            String responseStr = mpService.getHttpRequest().postWithToken(INVITE_WORKER_POST_URL,mpService.getConfigRepository().getAccessToken(),params);
            mpService.judgeValidParseJSON(responseStr, WxType.MP);
        }
    }

    @Override
    public void createSession(KefuSession session) {
        if(session != null){
            String params = session.toJSON();
            String responseStr = mpService.getHttpRequest().postWithToken(CREATE_SESSION_POST_URL,mpService.getConfigRepository().getAccessToken(),params);
            mpService.judgeValidParseJSON(responseStr, WxType.MP);
        }
    }

    @Override
    public void closeSession(KefuSession session) {
        if(session != null){
            String params = session.toJSON();
            String responseStr = mpService.getHttpRequest().postWithToken(CLOSE_SESSION_POST_URL,mpService.getConfigRepository().getAccessToken(),params);
            mpService.judgeValidParseJSON(responseStr, WxType.MP);
        }
    }

    @Override
    public KefuSession getSession(String openId) {
        if(Utils.isNotEmpty(openId)){
            String responseStr = mpService.getHttpRequest().get(String.format(GET_SESSION_GET_URL,mpService.getConfigRepository().getAccessToken(),openId));
            JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
            return json.toJavaObject(KefuSession.class);
        }
        return null;
    }

    @Override
    public List<KefuSession> getKefuSessionList(String account) {
        if(Utils.isNotEmpty(account)){
            String responseStr = mpService.getHttpRequest().get(String.format(GET_SESSION_LIST_GET_URL,mpService.getConfigRepository().getAccessToken(),account));
            JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
            JSONArray array = json.getJSONArray("sessionlist");
            if(Utils.isNotEmpty(array)){
               return array.toJavaList(KefuSession.class);
            }
        }
        return null;
    }

    @Override
    public KefuSessionWaitCase getWaitCase() {
        String responseStr = mpService.getHttpRequest().getWithToken(GET_WAIT_CASE_GET_URL,mpService.getConfigRepository().getAccessToken());
        JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
        return json.toJavaObject(KefuSessionWaitCase.class);
    }

    @Override
    public KefuMsgRecord getMsgList(KefuMsgRequest request) {
        if(request != null){
            String params = request.toJSON();
            String responseStr = mpService.getHttpRequest().postWithToken(GET_MSG_LIST_POST_URL,mpService.getConfigRepository().getAccessToken(),params);
            JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
            return json.toJavaObject(KefuMsgRecord.class);
        }
        return null;
    }

    @Override
    public void sendMessage(KefuMessage message) {
        if(message != null){
            String params = message.toJSON();
            String responseStr = mpService.getHttpRequest().postWithToken(SEND_MSG_POST_URL,mpService.getConfigRepository().getAccessToken(),params);
            mpService.judgeValidParseJSON(responseStr, WxType.MP);
        }
    }

    @Override
    public void sendMessage(KefuMessage message, String kfAccount) {
        if(message != null){
            JSONObject json = JSON.parseObject(message.toJSON()).fluentPut("customservice",new JSONObject().fluentPut("kf_account",kfAccount));
            String responseStr = mpService.getHttpRequest().postWithToken(SEND_MSG_POST_URL,mpService.getConfigRepository().getAccessToken(),json.toJSONString());
            mpService.judgeValidParseJSON(responseStr, WxType.MP);
        }
    }

    @Override
    public void cancelTyping(String openId) {
        typing(openId,"CancelTyping");
    }

    @Override
    public void typing(String openId) {
        typing(openId,"Typing");
    }

    private void typing(String openId,String command){
        if(Utils.isNotEmpty(openId)){
            JSONObject json = new JSONObject().fluentPut("touser",openId).fluentPut("command",command);
            String responseStr = mpService.getHttpRequest().postWithToken(TYPING_POST_URL,mpService.getConfigRepository().getAccessToken(),json.toJSONString());
            mpService.judgeValidParseJSON(responseStr, WxType.MP);
        }
    }
}
