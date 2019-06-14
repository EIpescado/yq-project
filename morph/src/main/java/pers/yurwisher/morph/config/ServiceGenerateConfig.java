package pers.yurwisher.morph.config;

/**
 * @author yq
 * @date 2019/06/13 14:20
 * @description
 * @since V1.0.0
 */
public class ServiceGenerateConfig{

    private Boolean service;
    private String servicePackageName;
    private String serviceName;
    private String superServiceClass;
    private GenerateConfig generateConfig;
    private String foClass;
    private String voClass;
    private String qoClass;
    private String soClass;
    private String toClass;

    public ServiceGenerateConfig(GenerateConfig generateConfig,Boolean service, String servicePackageName, String superServiceClass) {
        this.service = service == null ? true : service;
        this.servicePackageName = servicePackageName;
        this.superServiceClass = superServiceClass;
        this.generateConfig = generateConfig;
    }

    public Boolean getService() {
        return service;
    }

    public void setService(Boolean service) {
        this.service = service;
    }

    public String getServicePackageName() {
        return servicePackageName;
    }

    public void setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSuperServiceClass() {
        return superServiceClass;
    }

    public void setSuperServiceClass(String superServiceClass) {
        this.superServiceClass = superServiceClass;
    }

    public GenerateConfig getGenerateConfig() {
        return generateConfig;
    }

    public void setGenerateConfig(GenerateConfig generateConfig) {
        this.generateConfig = generateConfig;
    }

    public String getFoClass() {
        return foClass;
    }

    public void setFoClass(String foClass) {
        this.foClass = foClass;
    }

    public String getVoClass() {
        return voClass;
    }

    public void setVoClass(String voClass) {
        this.voClass = voClass;
    }

    public String getQoClass() {
        return qoClass;
    }

    public void setQoClass(String qoClass) {
        this.qoClass = qoClass;
    }

    public String getSoClass() {
        return soClass;
    }

    public void setSoClass(String soClass) {
        this.soClass = soClass;
    }

    public String getToClass() {
        return toClass;
    }

    public void setToClass(String toClass) {
        this.toClass = toClass;
    }

}
