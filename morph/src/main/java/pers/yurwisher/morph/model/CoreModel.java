package pers.yurwisher.morph.model;

import pers.yurwisher.morph.common.Constant;

import java.util.Date;

/**
 * @author yq
 * @date 2019/06/14 15:58
 * @description 基础数据模型
 * @since V1.0.0
 */
public class CoreModel {

    private String idType;
    /**
     * 作者
     */
    private String author;
    /**
     * 日期
     */
    private Date date;
    /**
     * 实体类全路径名称
     */
    private String entityClass;

    private String entityName;
    /**
     * 类描述
     */
    private String description;

    private String basePackage;

    public CoreModel(String idType, String author, String entityClass , String description, String basePackage) {
        this.idType = idType;
        this.author = author;
        this.date = new Date();
        this.entityClass = entityClass;
        this.entityName = entityClass.substring(entityClass.lastIndexOf(Constant.DOT) + 1);
        this.description = description;
        this.basePackage = basePackage;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

}
