package pers.yurwisher.morph.model.builder;

import pers.yurwisher.morph.model.CoreConfig;

public class CoreConfigBuilder {
    private Boolean vo;
    private Boolean qo;
    private Boolean fo;
    private Boolean so;
    private Boolean to;
    private String voSuperClass;
    private String qoSuperClass;
    private String foSuperClass;
    private String soSuperClass;
    private String toSuperClass;
    private String serviceSuperClass;
    private String serviceImplSuperClass;
    private String controllerSuperClass;
    private String mapperSuperClass;
    private String mapperLocation;
    private Boolean useLombok;

    public CoreConfigBuilder setVo(Boolean vo) {
        this.vo = vo;
        return this;
    }

    public CoreConfigBuilder setQo(Boolean qo) {
        this.qo = qo;
        return this;
    }

    public CoreConfigBuilder setFo(Boolean fo) {
        this.fo = fo;
        return this;
    }

    public CoreConfigBuilder setSo(Boolean so) {
        this.so = so;
        return this;
    }

    public CoreConfigBuilder setTo(Boolean to) {
        this.to = to;
        return this;
    }

    public CoreConfigBuilder setVoSuperClass(String voSuperClass) {
        this.voSuperClass = voSuperClass;
        return this;
    }

    public CoreConfigBuilder setQoSuperClass(String qoSuperClass) {
        this.qoSuperClass = qoSuperClass;
        return this;
    }

    public CoreConfigBuilder setFoSuperClass(String foSuperClass) {
        this.foSuperClass = foSuperClass;
        return this;
    }

    public CoreConfigBuilder setSoSuperClass(String soSuperClass) {
        this.soSuperClass = soSuperClass;
        return this;
    }

    public CoreConfigBuilder setToSuperClass(String toSuperClass) {
        this.toSuperClass = toSuperClass;
        return this;
    }

    public CoreConfigBuilder setServiceSuperClass(String serviceSuperClass) {
        this.serviceSuperClass = serviceSuperClass;
        return this;
    }

    public CoreConfigBuilder setControllerSuperClass(String controllerSuperClass) {
        this.controllerSuperClass = controllerSuperClass;
        return this;
    }

    public CoreConfigBuilder setUseLombok(Boolean useLombok) {
        this.useLombok = useLombok;
        return this;
    }

    public CoreConfigBuilder setServiceImplSuperClass(String serviceImplSuperClass) {
        this.serviceImplSuperClass = serviceImplSuperClass;
        return this;
    }

    public CoreConfigBuilder setMapperSuperClass(String mapperSuperClass) {
        this.mapperSuperClass = mapperSuperClass;
        return this;
    }

    public CoreConfigBuilder setMapperLocation(String mapperLocation) {
        this.mapperLocation = mapperLocation;
        return this;
    }

    public CoreConfig build() {
        return new CoreConfig(vo, qo, fo, so, to, voSuperClass, qoSuperClass, foSuperClass, soSuperClass, toSuperClass, serviceSuperClass,serviceImplSuperClass, controllerSuperClass, useLombok,mapperSuperClass,mapperLocation);
    }
}
