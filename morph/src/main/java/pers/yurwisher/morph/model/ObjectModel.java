package pers.yurwisher.morph.model;

import pers.yurwisher.morph.common.Constant;
import pers.yurwisher.morph.common.Utils;
import pers.yurwisher.morph.support.GeneratorHelper;

import java.util.List;
import java.util.Set;

/**
 * @author yq
 * @date 2019/06/14 16:02
 * @description 对象模型
 * @since V1.0.0
 */
public class ObjectModel {

    /**
     * 父类
     */
    private String superClass;

    /**
     * 是否使用Lombok
     */
    private Boolean useLombok;

    /**
     * 父类名称
     */
    private String superClassName;

    /**
     * 属性集合
     */
    private List<FieldInfo> fields;

    /**
     * 需要导入的包
     */
    private Set<String> needImportType;

    /**
     * 标识Vo,fo,to,so,qo
     */
    private String flag;

    private CoreModel coreModel;

    public ObjectModel(String superClass, Boolean useLombok,String flag,CoreModel coreModel) {
        this.superClass = superClass;
        this.useLombok = useLombok == null ? true : useLombok;
        this.superClassName = Utils.isEmpty(superClass) ? null : superClass.substring(superClass.lastIndexOf(Constant.DOT) + 1);
        this.flag = flag;
        this.coreModel = coreModel;
        GeneratorHelper.ImportTypeAndFieldInfo info = GeneratorHelper.getImportTypeAndFieldInfo(coreModel.getEntityClass(),superClass);
        this.setNeedImportType(info.getImportType());
        this.setFields(info.getFieldInfoList());
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public Boolean getUseLombok() {
        return useLombok;
    }

    public void setUseLombok(Boolean useLombok) {
        this.useLombok = useLombok;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public void setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
    }

    public List<FieldInfo> getFields() {
        return fields;
    }

    public void setFields(List<FieldInfo> fields) {
        this.fields = fields;
    }

    public Set<String> getNeedImportType() {
        return needImportType;
    }

    public void setNeedImportType(Set<String> needImportType) {
        this.needImportType = needImportType;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public CoreModel getCoreModel() {
        return coreModel;
    }

    public void setCoreModel(CoreModel coreModel) {
        this.coreModel = coreModel;
    }
}
