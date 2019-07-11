<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${coreModel.basePackage}.mapper.${coreModel.entityName}Mapper">
    <#if coreConfig.qo && coreConfig.to>
    <select id="list" resultType="${coreModel.basePackage}.pojo.to.${coreModel.entityName}To">

    </select>
    </#if>

    <#if coreConfig.vo>
    <select id="get" resultType="${coreModel.basePackage}.pojo.vo.${coreModel.entityName}Vo">

    </select>
    </#if>

    <#if coreConfig.so>
    <select id="select" resultType="${coreModel.basePackage}.pojo.so.${coreModel.entityName}So">

    </select>
    </#if>
</mapper>
