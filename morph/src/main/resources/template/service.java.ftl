package ${coreModel.basePackage}.service;

import ${coreModel.entityClass};
import ${superServiceClass};
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
public interface I${coreModel.entityName}Service extends ${superServiceName}<${coreModel.entityName}> {

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

    /**
     * 列表
     * @param qo 查询参数
     * @return 分页对象
     */
    PageR<${coreModel.entityName}To> list(${coreModel.entityName}Qo qo);

    /**
    * 下拉列表
    * @return 集合
    */
    List<${coreModel.entityName}So> select();

    /**
    * 详情
    * @param id 主键
    * @return ${coreModel.entityName}Vo
    */
    ${coreModel.entityName}Vo get(${coreModel.idType} id);

     /**
     * 删除
     * @param id 主键
     */
    void delete(${coreModel.idType} id);
}
