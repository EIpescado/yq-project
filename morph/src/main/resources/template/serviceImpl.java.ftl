package ${serviceImplPackageName};

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ${entityClass};
import ${mapperPackageName}.${entityName}Mapper;
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
@Service
public class ${serviceName} extends ${baseImplServiceName}<${entityName}Mapper,${entityName}> implements ${superServiceName}{
    <#if foClass??>
    /**
     * 新增
     * @param fo 参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(${entityName}Fo fo){

    }

    /**
     * 更新
     * @param id 主键
     * @param fo 参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(${idType} id,${entityName}Fo fo){

    }
    </#if>
    <#if qoClass??&&toClass??>
    /**
     * 列表
     * @param qo 查询参数
     * @return 分页对象
     */
    @Override
    public PageR<${entityName}To> list(${entityName}Qo qo){
        return null;
    }
    </#if>
    <#if soClass??>
    /**
    * 下拉列表
    * @return 集合
    */
    @Override
    public List<${entityName}So> select(){
        return null;
    }
    </#if>
    <#if voClass??>
    /**
    * 详情
    * @param id 主键
    * @return ${entityName}Vo
    */
    @Override
    public ${entityName}Vo get(${idType} id){
        return null;
    }
    </#if>
     /**
     * 删除
     * @param id 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(${idType} id){

    }
}
