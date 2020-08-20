package pers.yurwisher.wechat.mp.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量查询用户信息查询参数
 * @author yq 2020年7月20日 11:18:22
 */
@Data
public class WxMpUserQuery implements Serializable {

  private static final long serialVersionUID = -2846854192243381521L;
  @JSONField(name = "user_list")
  private List<WxMpUserQueryParam> useList;

  public WxMpUserQuery(List<String> openIds){
    useList = new ArrayList<>(openIds.size());
    for(String openId: openIds){
      useList.add(new WxMpUserQueryParam(openId));
    }
  }

  public List<WxMpUserQueryParam> getUseList() {
    return useList;
  }

  public void setUseList(List<WxMpUserQueryParam> useList) {
    this.useList = useList;
  }

  @Data
  public class WxMpUserQueryParam implements Serializable {
    private static final long serialVersionUID = 5330765578008166996L;
    private String openid;
    private String lang;

    public WxMpUserQueryParam(String openid, String lang) {
      this.openid = openid;
      this.lang = lang;
    }

    public WxMpUserQueryParam(String openid) {
      this.openid = openid;
      this.lang = "zh_CN";
    }

  }

}
