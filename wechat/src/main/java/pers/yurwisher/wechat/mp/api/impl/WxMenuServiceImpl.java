package pers.yurwisher.wechat.mp.api.impl;

import com.alibaba.fastjson.JSONObject;
import pers.yurwisher.wechat.common.enums.WxType;
import pers.yurwisher.wechat.common.utils.Utils;
import pers.yurwisher.wechat.mp.api.MpService;
import pers.yurwisher.wechat.mp.api.WxMenuService;
import pers.yurwisher.wechat.mp.menu.in.WxMenu;
import pers.yurwisher.wechat.mp.menu.out.WxMpGetSelfMenuInfoResult;
import pers.yurwisher.wechat.mp.menu.out.WxMpMenu;

/**
 * @author yq
 * @date 2018/07/30 10:09
 * @description 自定义菜单接口
 * @since V1.0.0
 */
public class WxMenuServiceImpl implements WxMenuService {

    private MpService mpService;

    public WxMenuServiceImpl(MpService mpService) {
        this.mpService = mpService;
    }

    @Override
    public String create(WxMenu wxMenu) {
        if (wxMenu != null && Utils.isNotEmpty(wxMenu.getButton())) {
            String url = wxMenu.getWxMenuRule() != null ? CREATE_CONDITIONAL_POST_URL : CREATE_POST_URL;
            String responseStr = mpService.getHttpRequest().postWithToken(url, mpService.getConfigRepository().getAccessToken(), wxMenu.toJSON());
            JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
            return json.getString("menuid");
        }
        return null;
    }

    @Override
    public WxMpMenu get() {
        String responseStr = mpService.getHttpRequest().getWithToken(GET_URL, mpService.getConfigRepository().getAccessToken());
        JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
        return json.toJavaObject(WxMpMenu.class);
    }

    @Override
    public void delete() {
        String responseStr = mpService.getHttpRequest().getWithToken(DELETE_GET_URL, mpService.getConfigRepository().getAccessToken());
        mpService.judgeValidParseJSON(responseStr, WxType.MP);
    }

    /**
     * menuid为菜单id，可以通过自定义菜单查询接口获取
     *
     * @param menuId 菜单id
     */
    @Override
    public void deleteConditional(String menuId) {
        if (Utils.isNotEmpty(menuId)) {
            String jsonParams = new JSONObject().fluentPut("menuid", menuId).toJSONString();
            String responseStr = mpService.getHttpRequest().postWithToken(DELETE_CONDITIONAL_POST_URL, mpService.getConfigRepository().getAccessToken(), jsonParams);
            mpService.judgeValidParseJSON(responseStr, WxType.MP);
        }
    }

    /**
     * 测试个性化菜单匹配结果
     *
     * @param userId user_id可以是粉丝的OpenID，也可以是粉丝的微信号。
     * @return WxMenu
     */
    @Override
    public WxMenu tryMatch(String userId) {
        if (Utils.isNotEmpty(userId)) {
            String jsonParams = new JSONObject().fluentPut("user_id", userId).toJSONString();
            String responseStr = mpService.getHttpRequest().postWithToken(TRY_MATCH_POST_URL, mpService.getConfigRepository().getAccessToken(), jsonParams);
            JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
            //接口文档为直接button返回,实际为外侧还有一层menu
            return json.getObject("menu", WxMenu.class);
        }
        return null;
    }

    /**
     * 获取自定义菜单配置
     *
     * @return 配置信息
     */
    @Override
    public WxMpGetSelfMenuInfoResult getCurrentSelfMenuInfo() {
        String responseStr = mpService.getHttpRequest().getWithToken(GET_CURRENT_SELF_MENU_INFO_GET_URL, mpService.getConfigRepository().getAccessToken());
        JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
        return json.toJavaObject(WxMpGetSelfMenuInfoResult.class);
    }

}
