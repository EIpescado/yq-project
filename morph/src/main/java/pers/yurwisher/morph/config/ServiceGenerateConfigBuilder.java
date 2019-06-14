package pers.yurwisher.morph.config;

public class ServiceGenerateConfigBuilder {
    private GenerateConfig generateConfig;
    private Boolean service;
    private String servicePackageName;
    private String superServiceClass;

    public ServiceGenerateConfigBuilder setGenerateConfig(GenerateConfig generateConfig) {
        this.generateConfig = generateConfig;
        return this;
    }

    public ServiceGenerateConfigBuilder setService(Boolean service) {
        this.service = service;
        return this;
    }

    public ServiceGenerateConfigBuilder setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
        return this;
    }

    public ServiceGenerateConfigBuilder setSuperServiceClass(String superServiceClass) {
        this.superServiceClass = superServiceClass;
        return this;
    }

    public ServiceGenerateConfig build() {
        return new ServiceGenerateConfig(generateConfig, service, servicePackageName, superServiceClass);
    }
}
