package pers.yurwisher.wechat.mp.material.out;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yq
 * @date 2020/08/18 17:16
 * @description 新增其他类型永久素材
 * @since V1.0.0
 */
@Data
public class WxMpAddMaterialResult implements Serializable {
    private static final long serialVersionUID = 3550007324308710689L;

    private String mediaId;

    private String url;
}
