package pers.yurwisher.morph.bean;

import java.util.Date;

/**
 * @author yq
 * @date 2019/06/13 15:42
 * @description
 * @since V1.0.0
 */
public class CoreInfo {

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

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }
}
