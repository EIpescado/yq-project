package pers.yurwisher.morph.model;

import pers.yurwisher.morph.common.Constant;
import pers.yurwisher.morph.common.Utils;

/**
 * @author yq
 * @date 2019/07/11 14:48
 * @description
 * @since V1.0.0
 */
public class MapperModel {

    private CoreModel coreModel;
    /**
     * 父类
     */
    private String superMapperClass;

    private String superMapperName;

    public MapperModel(CoreModel coreModel, String superMapperClass) {
        this.coreModel = coreModel;
        this.superMapperClass = superMapperClass;
        this.superMapperName = Utils.isEmpty(superMapperClass) ? null : superMapperClass.substring(superMapperClass.lastIndexOf(Constant.DOT) + 1);
    }

    public CoreModel getCoreModel() {
        return coreModel;
    }

    public void setCoreModel(CoreModel coreModel) {
        this.coreModel = coreModel;
    }

    public String getSuperMapperClass() {
        return superMapperClass;
    }

    public void setSuperMapperClass(String superMapperClass) {
        this.superMapperClass = superMapperClass;
    }

    public String getSuperMapperName() {
        return superMapperName;
    }

    public void setSuperMapperName(String superMapperName) {
        this.superMapperName = superMapperName;
    }
}
