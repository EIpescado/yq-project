package ${coreModel.basePackage}.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ${superServiceImplClass};
import ${coreModel.entityClass};
import ${coreModel.basePackage}.mapper.${coreModel.entityName}Mapper;
import ${coreModel.basePackage}.service.I${coreModel.entityName}Service;
import ${coreModel.basePackage}.pojo.fo.${coreModel.entityName}Fo;
import ${coreModel.basePackage}.pojo.qo.${coreModel.entityName}Qo;
import ${coreModel.basePackage}.pojo.so.${coreModel.entityName}So;
import ${coreModel.basePackage}.pojo.to.${coreModel.entityName}To;
import ${coreModel.basePackage}.pojo.vo.${coreModel.entityName}Vo;
import pers.yurwisher.wisp.wrapper.PageR;
import java.util.List;

/**
 * @author ${coreModel.author}
 * @date ${coreModel.date?string("yyyy-MM-dd HH:mm:ss")}
 * @description ${coreModel.description}
 * @since V1.0.0
 */
@Service
public class ${coreModel.entityName}ServiceImpl extends ${superServiceName}Impl<${coreModel.entityName}Mapper,${coreModel.entityName}> implements I${coreModel.entityName}Service{
    /**
     * 新增
     * @param fo 参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(${coreModel.entityName}Fo fo){

    }

    /**
     * 更新
     * @param id 主键
     * @param fo 参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(${coreModel.idType} id,${coreModel.entityName}Fo fo){

    }

    /**
     * 列表
     * @param qo 查询参数
     * @return 分页对象
     */
    @Override
    public PageR<${coreModel.entityName}To> list(${coreModel.entityName}Qo qo){
        return null;
    }

    /**
    * 下拉列表
    * @return 集合
    */
    @Override
    public List<${coreModel.entityName}So> select(){
        return null;
    }

    /**
    * 详情
    * @param id 主键
    * @return ${coreModel.entityName}Vo
    */
    @Override
    public ${coreModel.entityName}Vo get(${coreModel.idType} id){
        return null;
    }

     /**
     * 删除
     * @param id 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(${coreModel.idType} id){

    }
}
