package pers.yurwisher.token;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;


/**
 * @author yq
 * @date 2018/12/10 14:00
 * @description token凭证
 * @since V1.0.0
 */
@Data
public class Token {

    /**
     * 用户名
     */
    @JSONField(name = "un")
    private String userName;
    /**
     * 过期时间
     */
    @JSONField(name = "exp")
    private Long expiration;
    /**
     * 签发时间
     */
    @JSONField(name = "crt")
    private Long dateCreated;
    /**
     * token可以自动刷新,直到此时间为止
     */
    @JSONField(name = "rfu")
    private Long refreshUntil;

    /**
     * 转json对象
     *
     * @return JSONObject
     */
    public JSONObject toJSON() {
        return (JSONObject) JSON.toJSON(this);
    }

    /**
     * token是否有效
     *
     * @param lastPasswordReset 密码最后重置时间
     * @return token是否有效
     */
    public boolean validate(Date lastPasswordReset) {
        //已过期
        if (expiration <= System.currentTimeMillis()) {
            return false;
        } else {
            //token签发时间是否在重置密码之前
            boolean isCreatedBeforeLastPasswordReset = lastPasswordReset != null && dateCreated < lastPasswordReset.getTime();
            return !isCreatedBeforeLastPasswordReset;
        }
    }

    /**
     * 是否可以刷新token
     *
     * @return boolean
     */
    public boolean canRefresh() {
        return System.currentTimeMillis() <= this.refreshUntil;
    }
}
