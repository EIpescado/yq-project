package ${coreModel.basePackage}.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ${superServiceImplClass};
import ${coreModel.entityClass};
import ${coreModel.basePackage}.mapper.${coreModel.entityName}Mapper;
import ${coreModel.basePackage}.service.I${coreModel.entityName}Service;
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
import pers.yurwisher.wisp.utils.Asserts;
import pers.yurwisher.wisp.wrapper.PageR;

/**
 * @author ${coreModel.author}
 * @date ${coreModel.date?string("yyyy-MM-dd HH:mm:ss")}
 * @description ${coreModel.description}
 * @since V1.0.0
 */
@Service
public class ${coreModel.entityName}ServiceImpl extends ${superServiceName}Impl<${coreModel.entityName}Mapper,${coreModel.entityName}> implements I${coreModel.entityName}Service{

    <#if coreConfig.fo>
    /**
     * 新增
     * @param fo 参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(${coreModel.entityName}Fo fo){
        ${coreModel.entityName} ${coreModel.entityName?uncap_first} = new ${coreModel.entityName}();
        BeanUtils.copyProperties(fo,${coreModel.entityName?uncap_first});
        baseMapper.insert(${coreModel.entityName?uncap_first});
    }

    /**
     * 更新
     * @param id 主键
     * @param fo 参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(${coreModel.idType} id,${coreModel.entityName}Fo fo){
        ${coreModel.entityName} ${coreModel.entityName?uncap_first} = baseMapper.selectById(id);
        Asserts.notNull(${coreModel.entityName?uncap_first});
        BeanUtils.copyProperties(fo,${coreModel.entityName?uncap_first});
        baseMapper.updateById(${coreModel.entityName?uncap_first});
    }
    </#if>

    <#if coreConfig.qo && coreConfig.to>
    /**
     * 列表
     * @param qo 查询参数
     * @return 分页对象
     */
    @Override
    @SuppressWarnings("unchecked")
    public PageR<${coreModel.entityName}To> list(${coreModel.entityName}Qo qo){
        return super.toPageR(baseMapper.list(super.toPage(qo),qo));
    }
    </#if>

    <#if coreConfig.so>
    /**
    * 下拉列表
    * @return 集合
    */
    @Override
    public List<${coreModel.entityName}So> select(){
        return baseMapper.select();
    }
    </#if>

    <#if coreConfig.vo>
    /**
    * 详情
    * @param id 主键
    * @return ${coreModel.entityName}Vo
    */
    @Override
    public ${coreModel.entityName}Vo get(${coreModel.idType} id){
        return baseMapper.get(id);
    }
    </#if>

     /**
     * 删除
     * @param id 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(${coreModel.idType} id){
        baseMapper.deleteById(id);
    }
}
