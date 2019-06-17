package pers.yurwisher.morph.support;

import pers.yurwisher.morph.model.CoreConfig;
import pers.yurwisher.morph.model.CoreModel;

/**
 * @author yq
 * @date 2019/06/13 15:17
 * @description 生成器
 * @since V1.0.0
 */
public interface Generator {

    /**
     * 生成文件
     * @param coreModel 基础配置
     * @param config 其他配置
     */
    void generate(CoreModel coreModel, CoreConfig config);
}
