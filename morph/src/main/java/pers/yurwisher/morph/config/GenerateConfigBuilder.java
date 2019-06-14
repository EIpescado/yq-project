package pers.yurwisher.morph.config;

public class GenerateConfigBuilder {
    private String baseOutPutPath;
    private String author;
    private String description;
    private String entityClass;
    private String idType;

    public GenerateConfigBuilder setBaseOutPutPath(String baseOutPutPath) {
        this.baseOutPutPath = baseOutPutPath;
        return this;
    }

    public GenerateConfigBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public GenerateConfigBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public GenerateConfigBuilder setEntityClass(String entityClass) {
        this.entityClass = entityClass;
        return this;
    }

    public GenerateConfigBuilder setIdType(String idType) {
        this.idType = idType;
        return this;
    }

    public GenerateConfig build() {
        return new GenerateConfig(baseOutPutPath, author, description, entityClass, idType);
    }
}
