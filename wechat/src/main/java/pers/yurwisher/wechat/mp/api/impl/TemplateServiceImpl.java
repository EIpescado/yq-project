package pers.yurwisher.wechat.mp.api.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.wechat.common.enums.WxType;
import pers.yurwisher.wechat.common.utils.Utils;
import pers.yurwisher.wechat.mp.api.MpService;
import pers.yurwisher.wechat.mp.api.TemplateService;
import pers.yurwisher.wechat.mp.template.MpIndustry;
import pers.yurwisher.wechat.mp.template.Template;
import pers.yurwisher.wechat.mp.template.TemplateData;
import pers.yurwisher.wechat.mp.template.TemplateMessage;

import java.util.List;


/**
 * @author yq
 * @date 2018/07/06 14:16
 * @description 模版消息service
 * @since V1.0.0
 */
public class TemplateServiceImpl implements TemplateService {

    private final static Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);

    private MpService mpService;

    public TemplateServiceImpl(MpService mpService) {
        this.mpService = mpService;
    }

    /**
     * 获取公众号下所有消息模版
     *
     * @return 消息模版信息
     */
    @Override
    public List<Template> getAllTemplate() {
        String responseStr = mpService.getHttpRequest().getWithToken(GET_ALL_PRIVATE_TEMPLATE_URL, mpService.getConfigRepository().getAccessToken());
        JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
        JSONArray array = json.getJSONArray("template_list");
        if (Utils.isNotEmpty(array)) {
            List<Template> templateList = array.toJavaList(Template.class);
            logger.info("公众号下可用模版共:{}个", templateList.size());
            return templateList;
        } else {
            logger.info("公众号下无可用模版");
        }
        return null;
    }

    /**
     * 发送模版消息
     *
     * @param templateMessage 模版消息
     */
    @Override
    public void sendTemplateMessage(TemplateMessage templateMessage) {
        String messageJSON = JSON.toJSONString(templateMessage);
        String responseStr = mpService.getHttpRequest().postWithToken(SEND_TEMPLATE_MESSAGE_URL, mpService.getConfigRepository().getAccessToken(), messageJSON);
        JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
        logger.info("发送模版消息成功,消息ID:{}", json.getString("msgid"));
    }

    /**
     * 发送模版消息
     *
     * @param toUser           消息接受人openId
     * @param templateId       模版id
     * @param color            自体颜色
     * @param miniProgramAppId 小程序ID
     * @param pagePath         小程序页面路径
     * @param url              跳转地址
     * @param data             模版消息数据
     * @return 消息
     */
    @Override
    public TemplateMessage createTemplateMessage(String toUser, String templateId, String color, String miniProgramAppId,
                                                 String pagePath, String url, List<TemplateData> data) {
        TemplateMessage message = new TemplateMessage();
        message.setTouser(toUser);
        message.setTemplate_id(templateId);
        message.setMiniProgram(new TemplateMessage.MiniProgram(miniProgramAppId, pagePath));
        message.setUrl(url);
        message.setColor(color);
        JSONObject json = new JSONObject();
        if (Utils.isNotEmpty(data)) {
            data.forEach(td -> json.put(td.getKey(), td));
        }
        message.setData(json);
        return message;
    }

    /**
     * 发送模版消息
     *
     * @param toUser     消息接受人openId
     * @param templateId 模版id
     * @param url        跳转地址
     * @param data       模版消息数据
     * @return 消息
     */
    @Override
    public TemplateMessage createTemplateMessage(String toUser, String templateId, String url, List<TemplateData> data) {
        return createTemplateMessage(toUser, templateId, null, null, null, url, data);
    }

    @Override
    public TemplateMessage createTemplateMessage(String toUser, String templateId, String miniProgramAppId, String pagePath, List<TemplateData> data) {
        return createTemplateMessage(toUser, templateId, null, miniProgramAppId, pagePath, null, data);
    }

    /**
     * 获取所处行业信息 一个企业只可设置2个行业
     */
    @Override
    public MpIndustry getIndustry() {
        String responseStr = mpService.getHttpRequest().getWithToken(GET_INDUSTRY_URL, mpService.getConfigRepository().getAccessToken());
        JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
        return json.toJavaObject(MpIndustry.class);
    }

    /**
     * 设置所属行业
     *
     * @param industryId1 公众号模板消息所属行业编号
     * @param industryId2 公众号模板消息所属行业编号
     */
    @Override
    public void setIndustry(Integer industryId1, Integer industryId2) {
        JSONObject param = new JSONObject().fluentPut("industry_id1", industryId1).fluentPut("industry_id2", industryId2);
        String responseStr = mpService.getHttpRequest().postWithToken(API_SET_INDUSTRY_URL, mpService.getConfigRepository().getAccessToken(), param.toJSONString());
        mpService.judgeValidParseJSON(responseStr, WxType.MP);
    }

    /**
     * 删除模版
     *
     * @param templateId 模版id
     */
    @Override
    public void delTemplate(String templateId) {
        JSONObject param = new JSONObject().fluentPut("template_id", templateId);
        String responseStr = mpService.getHttpRequest().postWithToken(DEL_PRIVATE_TEMPLATE_URL, mpService.getConfigRepository().getAccessToken(), param.toJSONString());
        mpService.judgeValidParseJSON(responseStr, WxType.MP);
    }

}
