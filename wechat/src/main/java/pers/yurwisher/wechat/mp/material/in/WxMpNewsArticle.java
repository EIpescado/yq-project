package pers.yurwisher.wechat.mp.material.in;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.io.Serializable;

/**
 * 图文素材参数
 */
@Data
public class WxMpNewsArticle implements Serializable {

  private static final long serialVersionUID = 8162882600454034287L;
  /**
   * (必填) 图文消息的标题.
   */
  private String title;
  /**
   * (必填) 图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得.
   */
  @JSONField(name = "thumb_media_id")
  private String thumbMediaId;
  /**
   * 图文消息的作者.
   */
  private String author;

  /**
   * 图文消息的描述.
   */
  private String digest;
  /**
   * 是否显示封面，true为显示，false为不显示.
   */
  @JSONField(name = "show_cover_pic")
  private boolean showCoverPic;

  /**
   * (必填) 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源 "上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。
   */
  private String content;


  /**
   * 图文消息的原文地址，即点击“阅读原文”后的URL
   */
  @JSONField(name = "content_source_url")
  private String contentSourceUrl;

  /**
   * need_open_comment 不可修改
   * 是否打开评论，0不打开，1打开.
   */
  @JSONField(name = "need_open_comment")
  private Boolean needOpenComment;

  /**
   * only_fans_can_comment 不可修改
   * 是否粉丝才可评论，0所有人可评论，1粉丝才可评论.
   */
  @JSONField(name = "only_fans_can_comment")
  private Boolean onlyFansCanComment;

  /**
   * 修改时必填 要修改的图文消息的id
   */
  @JSONField(name = "media_id")
  private String mediaId;

  /**
   * 要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
   */
  private Integer index;
}
