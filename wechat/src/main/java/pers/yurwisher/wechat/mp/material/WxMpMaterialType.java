package pers.yurwisher.wechat.mp.material;

/**
 * @author yq
 * @date 2020/08/18 12:24
 * @description 素材格式
 * 公众号的素材库保存总数量有上限：图文消息素材、图片素材上限为100000，其他类型为1000。
 * 图片（image）: 10M，支持bmp/png/jpeg/jpg/gif格式
 * 语音（voice）：2M，播放长度不超过60s，mp3/wma/wav/amr格式
 * 视频（video）：10MB，支持MP4格式
 * 缩略图（thumb）：64KB，支持JPG格式
 * @since V1.0.0
 */
public enum WxMpMaterialType {
    IMAGE("image"),
    VOICE("voice"),
    VIDEO("video"),
    THUMB("thumb"),
        ;
    private String type;

    WxMpMaterialType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
