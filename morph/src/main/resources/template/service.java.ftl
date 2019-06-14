package ${servicePackageName};

import ${entityClass};
import ${superServiceClass};
<#if foClass??>
import ${foClass};
</#if>
<#if voClass??>
import ${voClass};
</#if>
<#if qoClass??>
import ${qoClass};
import pers.yurwisher.wisp.wrapper.PageR;
</#if>
<#if soClass??>
import ${soClass};
import java.util.List;
</#if>
<#if toClass??>
import ${toClass};
</#if>

/**
 * @author ${author}
 * @date ${date?string("yyyy-MM-dd HH:mm:ss")}
 * @description ${description}
 * @since V1.0.0
 */
public interface ${serviceName} extends ${superServiceName}<${entityName}> {
    <#if foClass??>
    /**
     * 新增
     * @param fo 参数
     */
    void create(${entityName}Fo fo);

    /**
     * 更新
     * @param id 主键
     * @param fo 参数
     */
    void update(${idType} id,${entityName}Fo fo);
    </#if>
    <#if qoClass??&&toClass??>
    /**
     * 列表
     * @param qo 查询参数
     * @return 分页对象
     */
    PageR<${entityName}To> list(${entityName}Qo qo);
    </#if>
    <#if soClass??>
    /**
    * 下拉列表
    * @return 集合
    */
    List<${entityName}So> select();
    </#if>
    <#if voClass??>
    /**
    * 详情
    * @param id 主键
    * @return ${entityName}Vo
    */
    ${entityName}Vo getUserConfig(${idType} id);
    </#if>
     /**
     * 删除
     * @param id 主键
     */
    void delete(${idType} id);
}
