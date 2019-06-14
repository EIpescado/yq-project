package pers.yurwisher.morph.bean;

/**
 * @author yq
 * @date 2019/06/13 13:57
 * @description serviceInfo
 * @since V1.0.0
 */
public class ServiceImplInfo extends CoreInfo{

    private String serviceImplPackageName;
    private String serviceName;
    private String superServiceName;
    private String superServiceClass;
    private String baseImplServiceName;
    private String mapperPackageName;

    private String foClass;
    private String voClass;
    private String qoClass;
    private String soClass;
    private String toClass;

    public String getServiceImplPackageName() {
        return serviceImplPackageName;
    }

    public void setServiceImplPackageName(String serviceImplPackageName) {
        this.serviceImplPackageName = serviceImplPackageName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSuperServiceName() {
        return superServiceName;
    }

    public void setSuperServiceName(String superServiceName) {
        this.superServiceName = superServiceName;
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

    public String getMapperPackageName() {
        return mapperPackageName;
    }

    public void setMapperPackageName(String mapperPackageName) {
        this.mapperPackageName = mapperPackageName;
    }

    public String getSuperServiceClass() {
        return superServiceClass;
    }

    public void setSuperServiceClass(String superServiceClass) {
        this.superServiceClass = superServiceClass;
    }

    public String getBaseImplServiceName() {
        return baseImplServiceName;
    }

    public void setBaseImplServiceName(String baseImplServiceName) {
        this.baseImplServiceName = baseImplServiceName;
    }

}
