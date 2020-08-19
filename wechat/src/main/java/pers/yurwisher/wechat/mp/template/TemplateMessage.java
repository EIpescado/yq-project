package pers.yurwisher.wechat.mp.template;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author yq
 * @date 2018/07/03 11:37
 * @description 模版消息
 * @since V1.0.0
 */
@Data
public class TemplateMessage {

    /**接收者openid*/
    private String touser ;

    /**模板ID*/
    private String template_id ;

    /**模板跳转链接*/
    private String url ;

    /**跳小程序所需数据，不需跳小程序可不用传该数据*/
    @JSONField(name = "miniprogram")
    private MiniProgram miniProgram;

    /**模板数据*/
    private Object data;

    /**	模板内容字体颜色，不填默认为黑色*/
    private String color;

    @Data
    public static class MiniProgram{
        /**
         * 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）
         */
        private String appid ;

        /**
         * 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），暂不支持小游戏
         */
        private String pagepath ;

        public MiniProgram(String appid, String pagepath) {
            this.appid = appid;
            this.pagepath = pagepath;
        }

    }

}
