package pers.yurwisher.wechat.mp.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信用户信息.
 *
 * @author yq 2020年7月20日 11:07:03
 */
@Data
public class WxMpUser implements Serializable {

  /**
   * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
   */
  private Boolean subscribe;
  /**
   * 用户的标识，对当前公众号唯一
   */
  private String openId;
  /**
   * 用户的昵称
   */
  private String nickname;
  /**
   * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
   */
  private String sex;
  /**
   * 用户所在城市
   */
  private String city;
  /**
   * 用户所在国家
   */
  private String country;
  /**
   * 用户所在省份
   */
  private String province;
  /**
   * 用户的语言，简体中文为zh_CN
   */
  private String language;
  /**
   * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，
   * 0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
   */
  private String headImgUrl;
  /**
   * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
   */
  private Long subscribeTime;
  /**
   * https://mp.weixin.qq.com/cgi-bin/announce?action=getannouncement&announce_id=11513156443eZYea&version=&lang=zh_CN
   * <pre>
   * 只有在将公众号绑定到微信开放平台帐号后，才会出现该字段。
   * 另外，在用户未关注公众号时，将不返回用户unionID信息。
   * 已关注的用户，开发者可使用“获取用户基本信息接口”获取unionID；
   * 未关注用户，开发者可使用“微信授权登录接口”并将scope参数设置为snsapi_userinfo，获取用户unionID
   * </pre>
   */
  private String unionId;
  /**
   * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
   */
  private String remark;
  /**
   * 用户所在的分组ID（兼容旧的用户分组接口）
   */
  private Integer groupId;

  /**
   * 用户被打上的标签ID列表
   */
  private Long[] tagid_list;

  /**
   * 返回用户关注的渠道来源，
   * ADD_SCENE_SEARCH 公众号搜索，
   * ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，
   * ADD_SCENE_PROFILE_CARD 名片分享，
   * ADD_SCENE_QR_CODE 扫描二维码，
   * ADD_SCENE_PROFILE_LINK 图文页内名称点击，
   * ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，
   * ADD_SCENE_PAID 支付后关注，
   * ADD_SCENE_WECHAT_ADVERTISEMENT 微信广告，
   * ADD_SCENE_OTHERS 其他
   */
  private String subscribeScene;

  /**
   * 二维码扫码场景（开发者自定义）
   */
  private String qrScene;

  /**
   * 二维码扫码场景描述（开发者自定义）
   */
  private String qrSceneStr;


}
