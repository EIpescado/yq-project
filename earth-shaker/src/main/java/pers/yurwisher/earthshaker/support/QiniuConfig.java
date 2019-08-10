package pers.yurwisher.earthshaker.support;

import lombok.Data;

/**
 * @author yq
 * @date 2019/08/08 15:09
 * @description 七牛配置
 * @since V1.0.0
 */
@Data
public class QiniuConfig {

    private String accessKey;
    private String secretKey;
    /**
     * 目标空间名
     */
    private String bucket;

    /**
     * 直接响应的报文,即不回调本方接口
     * 同callback只一个有效,
     * json 格式可自定义 参考 https://developer.qiniu.com/kodo/manual/1235/vars#magicvar
     */
    private String returnBody;

    /**
     * 文件上传成功后七牛回调本方的地址
     */
    private String callbackUrl;

    /**
     * 文件上传成功后七牛回调的请求体
     * json 格式可自定义 参考 https://developer.qiniu.com/kodo/manual/1235/vars#magicvar
     */
    private String callbackBody;

    /**
     * 过期时间默认3600s
     */
    private Long expires;

    /**
     * 机房位置
     */
    private ZoneEnum zone;

    private String saveKey;

}
