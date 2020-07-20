package pers.yurwisher.wechat.mp.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 关注者列表
 * @author yq 2020年7月20日 10:49:00
 */
@Data
public class WxMpUserList implements Serializable {

  private static final long serialVersionUID = 99614764552545688L;
  protected long total = -1;
  protected int count = -1;
  protected List<String> openids = new ArrayList<>();
  protected String nextOpenid;

}
