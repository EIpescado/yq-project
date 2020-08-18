package pers.yurwisher.wechat.mp.material.in;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yq
 * @date 2020/08/18 17:23
 * @description 视频素材额外参数
 * @since V1.0.0
 */
@Data
public class  WxMpVideoMaterialExtra implements Serializable {
    private static final long serialVersionUID = 4361325877115665827L;

    private String title;
    private String introduction;
}
