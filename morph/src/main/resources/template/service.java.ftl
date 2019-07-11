package ${coreModel.basePackage}.service;

import ${coreModel.entityClass};
import ${superServiceClass};
<#if coreConfig.fo>
import ${coreModel.basePackage}.pojo.fo.${coreModel.entityName}Fo;
</#if>
<#if coreConfig.qo>
import ${coreModel.basePackage}.pojo.qo.${coreModel.entityName}Qo;
</#if>
<#if coreConfig.so>
import ${coreModel.basePackage}.pojo.so.${coreModel.entityName}So;
import java.util.List;
</#if>
<#if coreConfig.to>
import ${coreModel.basePackage}.pojo.to.${coreModel.entityName}To;
</#if>
<#if coreConfig.vo>
import ${coreModel.basePackage}.pojo.vo.${coreModel.entityName}Vo;
</#if>
import pers.yurwisher.wisp.wrapper.PageR;


/**
 * @author ${coreModel.author}
 * @date ${coreModel.date?string("yyyy-MM-dd HH:mm:ss")}
 * @description ${coreModel.description}
 * @since V1.0.0
 */
public interface I${coreModel.entityName}Service extends ${superServiceName}<${coreModel.entityName}> {

    <#if coreConfig.fo>
    /**
     * 新增
     * @param fo 参数
     */
    void create(${coreModel.entityName}Fo fo);

    /**
     * 更新
     * @param id 主键
     * @param fo 参数
     */
    void update(${coreModel.idType} id,${coreModel.entityName}Fo fo);
    </#if>

    <#if coreConfig.qo && coreConfig.to>
    /**
     * 列表
     * @param qo 查询参数
     * @return 分页对象
     */
    PageR<${coreModel.entityName}To> list(${coreModel.entityName}Qo qo);
    </#if>

    <#if coreConfig.so>
    /**
    * 下拉列表
    * @return 集合
    */
    List<${coreModel.entityName}So> select();
    </#if>

    <#if coreConfig.vo>
    /**
    * 详情
    * @param id 主键
    * @return ${coreModel.entityName}Vo
    */
    ${coreModel.entityName}Vo get(${coreModel.idType} id);
    </#if>

     /**
     * 删除
     * @param id 主键
     */
    void delete(${coreModel.idType} id);
}
