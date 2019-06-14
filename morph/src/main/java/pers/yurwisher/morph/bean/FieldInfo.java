package pers.yurwisher.morph.bean;

import java.util.Objects;

/**
 * @author yq
 * @date 2019/06/11 11:37
 * @description
 * @since V1.0.0
 */
public class FieldInfo {

    private String propertyType;

    private String propertyName;

    public FieldInfo() {
    }

    public FieldInfo(String propertyType, String propertyName) {
        this.propertyType = propertyType;
        this.propertyName = propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldInfo fieldInfo = (FieldInfo) o;
        return Objects.equals(getPropertyName(), fieldInfo.getPropertyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPropertyName());
    }
}
