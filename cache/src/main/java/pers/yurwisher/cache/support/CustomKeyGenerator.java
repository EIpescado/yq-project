package pers.yurwisher.cache.support;

/**
 * @author yq
 * @date 2019/01/18 15:51
 * @description 自定义key生成器
 * @since V1.0.0
 */
public interface CustomKeyGenerator {

    /**
     * 生成key值
     * @return key
     */
     String generateKey();
}
