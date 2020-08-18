package pers.yurwisher.wechat.mp.api.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pers.yurwisher.wechat.common.enums.WxType;
import pers.yurwisher.wechat.mp.api.MaterialService;
import pers.yurwisher.wechat.mp.api.MpService;
import pers.yurwisher.wechat.mp.material.WxMpMaterialType;
import pers.yurwisher.wechat.mp.material.in.WxMpMaterialNews;
import pers.yurwisher.wechat.mp.material.in.WxMpVideoMaterialExtra;
import pers.yurwisher.wechat.mp.material.out.WxMpAddMaterialResult;

import java.io.InputStream;

/**
 * @author yq
 * @date 2020/08/18 16:28
 * @description 素材管理
 * @since V1.0.0
 */
public class MaterialServiceImpl implements MaterialService {

    private MpService mpService;

    public MaterialServiceImpl(MpService mpService) {
        this.mpService = mpService;
    }

    @Override
    public String addNews(WxMpMaterialNews news) {
        String responseStr = mpService.getHttpRequest().postWithToken(ADD_NEWS_POST_URL,mpService.getConfigRepository().getAccessToken(), JSON.toJSONString(news));
        JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
        return json.getString("media_id");
    }

    @Override
    public String uploadImg(InputStream stream) {
        String responseStr = mpService.getHttpRequest().uploadFileWithToken(UPLOAD_IMG_POST_URL,mpService.getConfigRepository().getAccessToken(),stream,"media");
        JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
        return json.getString("url");
    }

    @Override
    public WxMpAddMaterialResult addMaterial(InputStream inputStream, WxMpMaterialType type, WxMpVideoMaterialExtra extra) {
        String url = String.format(ADD_MATERIAL_POST_URL,mpService.getConfigRepository().getAccessToken(),type.toString());
        String responseStr = mpService.getHttpRequest().uploadFile(url,inputStream,"media","description",JSON.toJSONString(extra));
        JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
        return json.toJavaObject(WxMpAddMaterialResult.class);
    }
}
