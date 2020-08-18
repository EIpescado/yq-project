package pers.yurwisher.wechat.mp.menu.in;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.yurwisher.wechat.common.base.JsonBean;
import pers.yurwisher.wechat.mp.menu.WxMenuType;

import java.util.ArrayList;
import java.util.List;

/**
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013
 * 微信单个菜单
 * 1、自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。
 * 2、一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”代替。
 * 3、创建自定义菜单后，菜单的刷新策略是，在用户进入公众号会话页或公众号profile页时，
 *   如果发现上一次拉取菜单的请求在5分钟以前，就会拉取一下菜单，如果菜单有更新，就会刷新客户端的菜单。
 *   测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。
 * @author yq
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMenuButton extends JsonBean {

  private static final long serialVersionUID = 7530580665454777385L;

  /**
   * 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
   */
  @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
  private WxMenuType type;

  /**
   * 菜单标题，不超过16个字节，子菜单不超过60个字节
   */
  private String name;

  /**
   * 菜单KEY值，用于消息接口推送，不超过128字节
   */
  private String key;

  /**
   * 网页链接.
   * 用户点击菜单可打开链接，不超过1024字节。type为miniprogram时，不支持小程序的老版本客户端将打开本url。
   * view、miniprogram类型必须
   */
  private String url;

  /**
   * <pre>
   * 调用新增永久素材接口返回的合法media_id.
   * media_id类型和view_limited类型必须
   * </pre>
   */
  @JSONField(name = "media_id")
  private String mediaId;

  /**
   * 小程序的appid.
   * miniprogram类型必须
   */
  @JSONField(name = "appid")
  private String appId;

  /**
   * 小程序的页面路径.
   * miniprogram类型必须
   */
  @JSONField(name = "pagepath")
  private String pagePath;

  /**
   * 一级菜单下的二级菜单集合
   */
  @JSONField(name ="sub_button")
  private List<WxMenuButton> subButtons = new ArrayList<>();

}
