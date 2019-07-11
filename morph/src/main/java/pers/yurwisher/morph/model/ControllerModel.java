package pers.yurwisher.morph.model;

import pers.yurwisher.morph.common.Constant;
import pers.yurwisher.morph.common.Utils;

/**
 * @author yq
 * @date 2019/06/15 11:54
 * @description controller 模型
 * @since V1.0.0
 */
public class ControllerModel {

    private CoreModel coreModel;

    /**
     * 父类
     */
    private String superControllerClass;

    private String superControllerName;

    private CoreConfig coreConfig;

    public ControllerModel(CoreModel coreModel, String superControllerClass,CoreConfig coreConfig) {
        this.coreModel = coreModel;
        this.superControllerClass = superControllerClass;
        this.superControllerName = Utils.isEmpty(superControllerClass) ? null : superControllerClass.substring(superControllerClass.lastIndexOf(Constant.DOT) + 1);
        this.coreConfig = coreConfig;
    }

    public CoreModel getCoreModel() {
        return coreModel;
    }

    public void setCoreModel(CoreModel coreModel) {
        this.coreModel = coreModel;
    }

    public String getSuperControllerClass() {
        return superControllerClass;
    }

    public void setSuperControllerClass(String superControllerClass) {
        this.superControllerClass = superControllerClass;
    }

    public String getSuperControllerName() {
        return superControllerName;
    }

    public void setSuperControllerName(String superControllerName) {
        this.superControllerName = superControllerName;
    }

    public CoreConfig getCoreConfig() {
        return coreConfig;
    }

    public void setCoreConfig(CoreConfig coreConfig) {
        this.coreConfig = coreConfig;
    }
}
