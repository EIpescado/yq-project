package ${coreModel.basePackage}.pojo.${flag?lower_case};

<#if superClass??>
import ${superClass};
<#else>
import java.io.Serializable;
</#if>
<#list needImportType as type>
import ${type};
</#list>
<#if useLombok>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
</#if>
/**
 * @author ${coreModel.author}
 * @date ${coreModel.date?string("yyyy-MM-dd HH:mm:ss")}
 * @description ${coreModel.description} ${flag}
 * @since V1.0.0
 */
<#if useLombok>
@Data
    <#if superClass??>
@EqualsAndHashCode(callSuper = true)
    <#else>
@EqualsAndHashCode(callSuper = false)
    </#if>
@Accessors(chain = true)
</#if>
<#if superClassName??>
public class ${coreModel.entityName}${flag} extends ${superClassName} {
<#else>
public class ${coreModel.entityName}${flag} implements Serializable {
    private static final long serialVersionUID = 1L;
</#if>
<#list fields as field>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#if !useLombok>
    <#list fields as field>
    public ${field.propertyType} get${field.propertyName?cap_first}() {
        return ${field.propertyName};
    }

    public ${coreModel.entityName} set${field.propertyName?cap_first}(${field.propertyType} ${field.propertyName}) {
        this.${field.propertyName} = ${field.propertyName};
        return this;
    }
    </#list>
</#if>
<#if !useLombok>
    @Override
    public String toString() {
        return "${coreModel.entityName}{" +
        <#list fields as field>
            <#if field_index==0>
            "${field.propertyName}=" + ${field.propertyName} +
            <#else>
            ", ${field.propertyName}=" + ${field.propertyName} +
            </#if>
        </#list>
        "}";
    }
</#if>
}
