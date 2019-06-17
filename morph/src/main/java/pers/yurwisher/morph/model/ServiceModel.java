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

    public ServiceModel(CoreModel coreModel, String superServiceClass) {
        this.coreModel = coreModel;
        this.superServiceClass = superServiceClass;
        this.superServiceName = Utils.isEmpty(superServiceClass) ? null : superServiceClass.substring(superServiceClass.lastIndexOf(Constant.DOT) + 1);
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
}
