package pers.yurwisher.wechat.mp.api;

import pers.yurwisher.wechat.mp.material.WxMpMaterialType;
import pers.yurwisher.wechat.mp.material.in.WxMpMaterialNews;
import pers.yurwisher.wechat.mp.material.in.WxMpVideoMaterialExtra;
import pers.yurwisher.wechat.mp.material.out.WxMpAddMaterialResult;
import pers.yurwisher.wechat.mp.material.out.WxMpMaterialResult;

import java.io.InputStream;

/**
 * @author yq
 * @date 2020/08/18 14:30
 * @description 素材service
 * @since V1.0.0
 */
public interface MaterialService {

    /**
     * 新增永久图文素材
     */
    String ADD_NEWS_POST_URL = "https://api.weixin.qq.com/cgi-bin/material/add_news";

    /**
     * 上传图文消息内的图片获取URL
     */
    String UPLOAD_IMG_POST_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg";

    /**
     *  新增其他类型永久素材
     */
    String ADD_MATERIAL_POST_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%1$s&type=%2$s";

    /**获取永久素材信息*/
    String GET_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/get_material";

    /**
     * 新增永久图文素材
     * @param news 参数
     * @return mediaId
     */
    String addNews(WxMpMaterialNews news);

    /**
     * 上传图文消息内的图片获取URL
     * @param stream 流
     * @return URL 上传图片的URL，可放置图文消息中使用
     */
    String uploadImg(InputStream stream);

    /**
     * 新增其他类型永久素材
     * @param inputStream 输入流
     * @param type 类型 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param extra 视频素材额外参数,非视频可不传
     * @return 结果
     */
    WxMpAddMaterialResult addMaterial(InputStream inputStream, WxMpMaterialType type, WxMpVideoMaterialExtra extra);

    WxMpMaterialResult getMaterial(String mediaId);
}
