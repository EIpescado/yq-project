package ${coreModel.basePackage}.mapper;

import ${coreModel.entityClass};
import ${superMapperClass};
import ${coreModel.basePackage}.pojo.qo.${coreModel.entityName}Qo;
import ${coreModel.basePackage}.pojo.so.${coreModel.entityName}So;
import ${coreModel.basePackage}.pojo.to.${coreModel.entityName}To;
import ${coreModel.basePackage}.pojo.vo.${coreModel.entityName}Vo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author ${coreModel.author}
 * @date ${coreModel.date?string("yyyy-MM-dd HH:mm:ss")}
 * @description ${coreModel.description} Mapper
 * @since V1.0.0
 */
public interface ${coreModel.entityName}Mapper extends ${superMapperName}<${coreModel.entityName}> {

    /**
     * 列表
     * @param page mybatis-plus分页参数
     * @param qo 查询参数
     * @return 列表
     */
    IPage<${coreModel.entityName}To> list(Page page, @Param("qo") ${coreModel.entityName}Qo qo);

    /**
    * 详情
    * @param id ID
    * @return 详情
    */
    ${coreModel.entityName}Vo get(@Param("id")Long id);

    /**
    * 下拉框
    * @return 集合
    */
    List<${coreModel.entityName}So> select();
}
