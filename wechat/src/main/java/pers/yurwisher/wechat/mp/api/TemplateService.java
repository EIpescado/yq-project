package pers.yurwisher.wechat.mp.api;

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
public interface TemplateService {

    /**
     * 获取拥有的所有模版 接口地址 get
     */
    String GET_ALL_PRIVATE_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template";

    /**
     * 发送模版消息 接口地址 post
     */
    String SEND_TEMPLATE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send";

    /**
     * 获取所属行业 接口地址 get
     */
    String GET_INDUSTRY_URL = "https://api.weixin.qq.com/cgi-bin/template/get_industry";

    /**
     * 设置所属行业 接口地址 post
     */
    String API_SET_INDUSTRY_URL = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry";

    /**
     * 删除模版 接口地址 post
     */
    String DEL_PRIVATE_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/template/del_private_template";


    /**
     * 获取公众号下所有消息模版
     *
     * @return 消息模版信息
     */
    List<Template> getAllTemplate();

    /**
     * 发送模版消息
     *
     * @param templateMessage 模版消息
     */
    void sendTemplateMessage(TemplateMessage templateMessage);

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
    TemplateMessage createTemplateMessage(String toUser, String templateId, String color, String miniProgramAppId,
                                          String pagePath, String url, List<TemplateData> data);

    /**
     * 发送模版消息
     *
     * @param toUser     消息接受人openId
     * @param templateId 模版id
     * @param url        跳转地址
     * @param data       模版消息数据
     * @return 消息
     */
    TemplateMessage createTemplateMessage(String toUser, String templateId, String url, List<TemplateData> data);


    /**
     * 发送模版消息
     *
     * @param toUser     消息接受人openId
     * @param templateId 模版id
     * @param miniProgramAppId 小程序appId
     * @param pagePath 点击消息跳转的小程序页面
     * @param data       模版消息数据
     * @return 消息
     */
    TemplateMessage createTemplateMessage(String toUser, String templateId, String miniProgramAppId,
                                          String pagePath, List<TemplateData> data);

    /**
     * 获取所处行业信息 一个企业只可设置2个行业
     */
    MpIndustry getIndustry();

    /**
     * 设置所属行业
     *
     * @param industryId1 公众号模板消息所属行业编号
     * @param industryId2 公众号模板消息所属行业编号
     */
    void setIndustry(Integer industryId1, Integer industryId2);

    /**
     * 删除模版
     *
     * @param templateId 模版id
     */
    void delTemplate(String templateId);
}
