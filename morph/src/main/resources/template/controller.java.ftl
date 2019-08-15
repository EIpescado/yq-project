package ${coreModel.basePackage}.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pers.yurwisher.wisp.wrapper.R;
<#if coreConfig.fo>
import ${coreModel.basePackage}.pojo.fo.${coreModel.entityName}Fo;
</#if>
<#if coreConfig.qo>
import ${coreModel.basePackage}.pojo.qo.${coreModel.entityName}Qo;
</#if>
import ${superControllerClass};
import ${coreModel.basePackage}.service.I${coreModel.entityName}Service;

/**
 * @author ${coreModel.author}
 * @date ${coreModel.date?string("yyyy-MM-dd HH:mm:ss")}
 * @description ${coreModel.description}
 * @since V1.0.0
 */
@RestController
@RequestMapping("/${coreModel.entityName?uncap_first}")
<#if superControllerName??>
public class ${coreModel.entityName}Controller extends ${superControllerName}{
<#else>
public class ${coreModel.entityName}Controller{
</#if>
    private I${coreModel.entityName}Service ${coreModel.entityName?uncap_first}Service;

    public ${coreModel.entityName}Controller(I${coreModel.entityName}Service ${coreModel.entityName?uncap_first}Service) {
        this.${coreModel.entityName?uncap_first}Service = ${coreModel.entityName?uncap_first}Service;
    }

    <#if coreConfig.fo>
    @PostMapping
    public R create(@RequestBody ${coreModel.entityName}Fo fo){
        ${coreModel.entityName?uncap_first}Service.create(fo);
        return R.ok();
    }

    @PostMapping("{id}")
    public R update(@PathVariable(name = "id")${coreModel.idType} id, @RequestBody ${coreModel.entityName}Fo fo){
        ${coreModel.entityName?uncap_first}Service.update(id,fo);
        return R.ok();
    }
    </#if>

    <#if coreConfig.vo>
    @GetMapping("{id}")
    public R get(@PathVariable(name = "id")${coreModel.idType} id){
        return R.ok(${coreModel.entityName?uncap_first}Service.get(id));
    }
    </#if>

    @PostMapping("/delete/{id}")
    public R delete(@PathVariable(name = "id")${coreModel.idType} id){
        ${coreModel.entityName?uncap_first}Service.delete(id);
        return R.ok();
    }

    <#if coreConfig.qo>
    @GetMapping
    public R list(@ModelAttribute ${coreModel.entityName}Qo qo){
        return R.ok(${coreModel.entityName?uncap_first}Service.list(qo));
    }
    </#if>

    <#if coreConfig.so>
    @GetMapping("/select")
    public R select(){
        return R.ok(${coreModel.entityName?uncap_first}Service.select());
    }
    </#if>

}
