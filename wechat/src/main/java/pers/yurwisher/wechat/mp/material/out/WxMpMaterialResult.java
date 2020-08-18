package pers.yurwisher.wechat.mp.material.out;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yq
 * @date 2020/08/18 17:16
 * @description 永久素材详情
 * @since V1.0.0
 */
@Data
public class WxMpMaterialResult implements Serializable {
    private static final long serialVersionUID = 3550007324308710689L;

    private Long createTime;

    private Long updateTime;

    private List<WxMpNewsItem> newsItems;

    /**
     *  视频素材 标题
     */
    private String title;

    /**
     * 视频素材 描述
     */
    private String description;

    /**
     * 视频素材 下载地址
     */
    private String downUrl;
}
