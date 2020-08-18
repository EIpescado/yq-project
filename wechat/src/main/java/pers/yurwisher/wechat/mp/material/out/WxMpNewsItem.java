package pers.yurwisher.wechat.mp.material.out;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yq
 * @date 2020/08/18 21:48
 * @description 图文信息详情
 * @since V1.0.0
 */
@Data
public class WxMpNewsItem implements Serializable {
    private static final long serialVersionUID = 1588598875652196336L;

    /**
     * 图文消息的标题
     */
    private String title;
    /**
     * 图文消息的封面图片素材id（必须是永久mediaID）
     */
    private String thumbMediaId;
    /**
     * 是否显示封面，0为false，即不显示，1为true，即显示
     */
    private Boolean showCoverPic;
    /**
     * 作者
     */
    private String author;

    /**
     * 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
     */
    private String digest;
    /**
     * 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
     */
    private String content;
    /**
     * 图文页的URL
     */
    private String url;
    /**
     * 图文消息的原文地址，即点击阅读原文后的URL
     */
    private String contentSourceUrl;
}
