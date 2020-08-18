package pers.yurwisher.wechat.mp.material.in;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 新增图文素材.参数
 * @author yq
 */
@Data
public class WxMpMaterialNews implements Serializable {

  private static final long serialVersionUID = 260771298488470731L;
  private List<WxMpNewsArticle> articles ;


}
