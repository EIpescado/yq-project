package pers.yurwisher.earthshaker.service.impl;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.extern.slf4j.Slf4j;
import pers.yurwisher.earthshaker.support.QiniuConfig;
import pers.yurwisher.earthshaker.Utils;
import pers.yurwisher.earthshaker.support.ZoneEnum;
import pers.yurwisher.earthshaker.exception.EarthShakerException;
import pers.yurwisher.earthshaker.service.IQiniuService;

import java.io.InputStream;

/**
 * @author yq
 * @date 2019/08/08 15:13
 * @description 七牛service
 * @since V1.0.0
 */
@Slf4j
public class QiniuServiceImpl implements IQiniuService {
    private QiniuConfig config;
    private StringMap putPolicy;
    private Zone defaultZone;

    public QiniuServiceImpl(QiniuConfig config) {
        this.config = config;
        this.putPolicy = putPolicy();
        this.defaultZone = getZone(config.getZone());
    }

    @Override
    public String getUploadToken() {
        return getUploadToken(config.getBucket());
    }

    @Override
    public String getUploadToken(String bucket) {
        Auth auth = Auth.create(config.getAccessKey(), config.getSecretKey());
        return auth.uploadToken(bucket,null,config.getExpires(),putPolicy);
    }

    @Override
    public String getUploadCoverToken(String key) {
        return getUploadCoverToken(config.getBucket(),key);
    }

    @Override
    public String getUploadCoverToken(String bucket, String key) {
        Auth auth = Auth.create(config.getAccessKey(), config.getSecretKey());
        return auth.uploadToken(bucket,key,config.getExpires(),putPolicy);
    }

    @Override
    public String upload(InputStream inputStream) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(defaultZone);
        UploadManager uploadManager = new UploadManager(cfg);
        //获取上传凭证
        String upToken = getUploadToken();
        try {
            //默认不指定key的情况下，以文件内容的hash值作为文件名
            Response response = uploadManager.put(inputStream,null,upToken,null, null);
            return response.bodyString();
        } catch (QiniuException e) {
            log.error("qiniu upload file error",e);
            throw new EarthShakerException("qiniu upload file error");
        }
    }

    private StringMap putPolicy(){
        if(!Utils.isNotEmpty(config.getReturnBody()) && !Utils.isNotEmpty(config.getCallbackUrl())){
            throw new EarthShakerException("returnBody and callbackUrl must config one of it");
        }
        if(Utils.isNotEmpty(config.getReturnBody()) && Utils.isNotEmpty(config.getCallbackUrl())){
            throw new EarthShakerException("returnBody and callbackUrl just can config one of it");
        }
        StringMap putPolicy = new StringMap();
        if(Utils.isNotEmpty(config.getReturnBody())){
            putPolicy.put("returnBody",config.getReturnBody());
        }
        if(Utils.isNotEmpty(config.getCallbackUrl())){
            putPolicy.put("callbackUrl", config.getCallbackUrl());
            if(!Utils.isNotEmpty(config.getCallbackBody())){
                throw new EarthShakerException("callbackBody can't be empty");
            }
            putPolicy.put("callbackBody", config.getCallbackBody());
            //回调 请求体类型
            putPolicy.put("callbackBodyType", "application/json");
        }
        if(Utils.isNotEmpty(config.getSaveKey())){
            putPolicy.put("saveKey", config.getSaveKey());
        }
        return putPolicy;
    }

    private Zone getZone(ZoneEnum zoneEnum){
        Zone zone;
        switch (zoneEnum){
            case zone0:
                zone = Zone.zone0();
                break;
            case zone1:
                zone = Zone.zone1();
                break;
            case zone2:
                zone = Zone.zone2();
                break;
            case zoneNa0:
                zone = Zone.zoneNa0();
                break;
            case zoneAs0:
                zone = Zone.zoneAs0();
                break;
               default:
                   zone = Zone.zone0();
        }
        return zone;
    }
}
