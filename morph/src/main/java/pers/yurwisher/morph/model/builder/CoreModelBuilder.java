package pers.yurwisher.morph.model.builder;

import pers.yurwisher.morph.model.CoreModel;

public class CoreModelBuilder {
    private String idType;
    private String author;
    private String entityClass;
    private String description;
    private String basePackage;

    public CoreModelBuilder setIdType(String idType) {
        this.idType = idType;
        return this;
    }

    public CoreModelBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public CoreModelBuilder setEntityClass(String entityClass) {
        this.entityClass = entityClass;
        return this;
    }

    public CoreModelBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CoreModelBuilder setBasePackage(String basePackage) {
        this.basePackage = basePackage;
        return this;
    }

    public CoreModel build() {
        return new CoreModel(idType, author, entityClass, description, basePackage);
    }
}
