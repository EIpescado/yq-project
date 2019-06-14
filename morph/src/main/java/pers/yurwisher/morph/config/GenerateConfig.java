package pers.yurwisher.morph.config;

import java.util.Date;

/**
 * @author yq
 * @date 2019/06/13 14:50
 * @description 基础生成配置
 * @since V1.0.0
 */
public class GenerateConfig {

    private String idType;
    /**
     * 文件输出根路径
     */
    private String baseOutPutPath;
    private String author;
    private String description;
    /**
     * 实体类全路径名
     */
    private String entityClass;
    private Date date;

    public GenerateConfig(String baseOutPutPath, String author, String description, String entityClass,String idType) {
        this.baseOutPutPath = baseOutPutPath;
        this.author = author;
        this.description = description;
        this.entityClass = entityClass;
        this.idType = idType;
        this.date = new Date();
    }

    public String getBaseOutPutPath() {
        return baseOutPutPath;
    }

    public void setBaseOutPutPath(String baseOutPutPath) {
        this.baseOutPutPath = baseOutPutPath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }
}
