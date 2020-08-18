package pers.yurwisher.wechat.mp.api;


import pers.yurwisher.wechat.mp.kf.KefuAccount;
import pers.yurwisher.wechat.mp.kf.KefuMessage;
import pers.yurwisher.wechat.mp.kf.KefuMsgRecord;
import pers.yurwisher.wechat.mp.kf.KefuMsgRequest;
import pers.yurwisher.wechat.mp.kf.KefuSession;
import pers.yurwisher.wechat.mp.kf.KefuSessionWaitCase;

import java.io.InputStream;
import java.util.List;

/**
 * @author yq
 * @date 2018/07/30 16:52
 * @description 客服帐号管理 必须先在公众平台官网为公众号设置微信号后才能使用该能力。
 * @since V1.0.0
 */
public interface KefuService {

    /**
     * 添加
     */
    String ADD_POST_URL = "https://api.weixin.qq.com/customservice/kfaccount/add";
    /**
     * 更新
     */
    String UPDATE_POST_URL = "https://api.weixin.qq.com/customservice/kfaccount/update";
    /**
     * 删除
     */
    String DELETE_GET_URL = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=%1$s&kf_account=%2$s";
    /**
     * 设置客服帐号的头像
     */
    String UPLOAD_HEAD_IMG_POST_URL = "http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=%1$s&kf_account=%2$s";

    /**
     * 获取客服基本信息
     */
    String GET_KEFU_LIST_GET_URL = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist";

    /**
     * 获取在线客服信息
     */
    String GET_ONLINE_KF_LIST_GET_URL = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist";

    /**
     * 邀请绑定客服帐号
     */
    String INVITE_WORKER_POST_URL = "https://api.weixin.qq.com/customservice/kfaccount/inviteworker";

    /**
     * 创建客服会话
     */
    String CREATE_SESSION_POST_URL = "https://api.weixin.qq.com/customservice/kfsession/create";
    /**
     * 关闭客服会话
     */
    String CLOSE_SESSION_POST_URL = "https: //api.weixin.qq.com/customservice/kfsession/close";
    /**
     * 获取用户会话
     */
    String GET_SESSION_GET_URL = "https://api.weixin.qq.com/customservice/kfsession/getsession?access_token=%1$s&openid=%2$s";

    /**
     * 获取客服会话列表
     */
    String GET_SESSION_LIST_GET_URL = "https://api.weixin.qq.com/customservice/kfsession/getsessionlist?access_token=%1$s&kf_account=%2$s";

    /**
     * 获取未接入会话列表
     */
    String GET_WAIT_CASE_GET_URL = "https://api.weixin.qq.com/customservice/kfsession/getwaitcase";

    /**
     * 获取聊天记录
     */
    String GET_MSG_LIST_POST_URL = "https://api.weixin.qq.com/customservice/msgrecord/getmsglist";
    /**
     * 客服发送消息
     */
    String SEND_MSG_POST_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
    /**
     * 客服输入状态
     */
    String TYPING_POST_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/typing";

    /**
     * 添加客服帐号
     * @param account  客服帐号 ,帐号,昵称必填
     */
    void add(KefuAccount account);

    /**
     *  修改客服帐号 帐号,昵称必填
     * @param account 客服帐号
     */
    void update(KefuAccount account);

    /**
     * 删除客服帐号
     * @param account 客服帐号
     */
    void del(String account);

    /**
     * 设置客服帐号的头像
     * @param kfAccount 客服帐号
     * @param imgFile File 头像图片文件必须是jpg格式 推荐使用640*640大小的图片以达到最佳效果
     */
    void uploadHeadImg(String kfAccount, InputStream imgFile);

    /**
     * 获取所有客服账号
     * @param getAll 是否获取所有 false则获取当前在线客服信息
     * @return 客服账号
     */
    List<KefuAccount> getKefuList(boolean getAll);

    /**
     * 邀请绑定客服帐号
     * @param account 客服帐号,完整客服帐号,接收绑定邀请的客服微信号必填
     * 新添加的客服帐号是不能直接使用的，只有客服人员用微信号绑定了客服账号后，方可登录Web客服进行操作。
     * 此接口发起一个绑定邀请到客服人员微信号，客服人员需要在微信客户端上用该微信号确认后帐号才可用。
     * 尚未绑定微信号的帐号可以进行绑定邀请操作，邀请未失效时不能对该帐号进行再次绑定微信号邀请
     */
    void inviteWorker(KefuAccount account);

    /**
     * 创建会话
     * @param session 客服会话,客服帐号及粉丝openId
     */
    void createSession(KefuSession session);

    /**
     * 关闭会话
     * @param session 客服会话,客服帐号及粉丝openId
     */
    void closeSession(KefuSession session);

    /**
     * 获取用户会话状态
     * @param openId 粉丝openId
     * @return 会话
     */
    KefuSession getSession(String openId);

    /**
     *  获取客服所有会话
     * @param account 客服帐号
     * @return 客服会话
     */
    List<KefuSession> getKefuSessionList(String account);

    /**
     * 获取未接入会话列表
     * @return 会话
     */
    KefuSessionWaitCase getWaitCase();

    /**
     * 获取客服聊天记录
     * 此接口返回的聊天记录中，对于图片、语音、视频，分别展示成文本格式的[image]、[voice]、[video]
     * @param request 请求参数
     * @return 客服聊天记录
     */
    KefuMsgRecord getMsgList(KefuMsgRequest request);

    /**
     * 客服发送消息
     * @param message 消息
     */
    void sendMessage(KefuMessage message);

    /**
     * 指定客服发送消息
     * @param message 消息
     * @param kfAccount 客服帐号
     */
    void sendMessage(KefuMessage message, String kfAccount);

    /**
     * 对用户下发“正在输入"状态
     * 开发者可通过调用“客服输入状态”接口，返回客服当前输入状态给用户。
     * 此接口需要客服消息接口权限。
     * 如果不满足发送客服消息的触发条件，则无法下发输入状态。
     * 下发输入状态，需要客服之前30秒内跟用户有过消息交互。
     * 在输入状态中（持续15s），不可重复下发输入态。
     * 在输入状态中，如果向用户下发消息，会同时取消输入状态。
     * @param openId 用户openId
     */
    void typing(String openId);

    /**
     * 取消对用户的”正在输入"状态
     * @param openId 用户openId
     */
    void cancelTyping(String openId);
}
