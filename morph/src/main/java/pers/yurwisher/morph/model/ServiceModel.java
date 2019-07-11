package pers.yurwisher.morph.model;

import pers.yurwisher.morph.common.Constant;
import pers.yurwisher.morph.common.Utils;

/**
 * @author yq
 * @date 2019/06/15 11:54
 * @description service 模型
 * @since V1.0.0
 */
public class ServiceModel {

    private CoreModel coreModel;

    /**
     * 父类
     */
    private String superServiceClass;

    private String superServiceName;

    private String superServiceImplClass;

    private CoreConfig coreConfig;

    public ServiceModel(CoreModel coreModel, String superServiceClass,String superServiceImplClass,CoreConfig coreConfig) {
        this.coreModel = coreModel;
        this.superServiceClass = superServiceClass;
        this.superServiceImplClass = superServiceImplClass;
        this.superServiceName = Utils.isEmpty(superServiceClass) ? null : superServiceClass.substring(superServiceClass.lastIndexOf(Constant.DOT) + 1);
        this.coreConfig = coreConfig;
    }

    public CoreModel getCoreModel() {
        return coreModel;
    }

    public void setCoreModel(CoreModel coreModel) {
        this.coreModel = coreModel;
    }

    public String getSuperServiceClass() {
        return superServiceClass;
    }

    public void setSuperServiceClass(String superServiceClass) {
        this.superServiceClass = superServiceClass;
    }

    public String getSuperServiceName() {
        return superServiceName;
    }

    public void setSuperServiceName(String superServiceName) {
        this.superServiceName = superServiceName;
    }

    public String getSuperServiceImplClass() {
        return superServiceImplClass;
    }

    public void setSuperServiceImplClass(String superServiceImplClass) {
        this.superServiceImplClass = superServiceImplClass;
    }

    public CoreConfig getCoreConfig() {
        return coreConfig;
    }

    public void setCoreConfig(CoreConfig coreConfig) {
        this.coreConfig = coreConfig;
    }
}
