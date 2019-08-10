package pers.yurwisher.earthshaker.service;

import java.io.InputStream;

/**
 * @author yq
 * @date 2019/08/08 15:12
 * @description 七牛service
 * @since V1.0.0
 */
public interface IQiniuService {

    /**
     * 获取上传凭证
     * @return 凭证
     */
    String getUploadToken();

    /**
     * 获取指定bucket的上传凭证
     * @param bucket 目标空间名
     * @return 凭证
     */
    String getUploadToken(String bucket);

    /**
     * 获取覆盖上传的凭证
     * @param key 文件保存在空间中的资源名
     * @return 凭证
     */
    String getUploadCoverToken(String key);

    /**
     * 获取覆盖上传的凭证
     * @param bucket 目标空间名
     * @param key 文件保存在空间中的资源名
     * @return 凭证
     */
    String getUploadCoverToken(String bucket,String key);

    /**
     * 服务端上传文件到七牛
     * @return 七牛的响应结果
     */
    String upload(InputStream inputStream);

}


