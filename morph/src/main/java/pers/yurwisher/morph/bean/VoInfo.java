package pers.yurwisher.morph.bean;

import java.util.List;
import java.util.Set;

/**
 * @author yq
 * @date 2019/06/12 15:48
 * @description vo.java.ftl需要的信息
 * @since V1.0.0
 */
public class VoInfo extends CoreInfo{

    private String packageName;
    /**
     * 是否使用Lombok
     */
    private boolean useLombok;

    /**
     * 父类名称
     */
    private String superClassName;

    private String superClass;

    /**
     * 属性集合
     */
    private List<FieldInfo> fields;

    /**
     * 需要导入的包
     */
    private Set<String> needImportType;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isUseLombok() {
        return useLombok;
    }

    public void setUseLombok(boolean useLombok) {
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

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }
}
