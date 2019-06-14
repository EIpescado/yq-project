package pers.yurwisher.morph.config;

public class PojoGenerateConfigBuilder {
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
    private String packageName;
    private Boolean useLombok;
    private GenerateConfig generateConfig;

    public PojoGenerateConfigBuilder setVo(Boolean vo) {
        this.vo = vo;
        return this;
    }

    public PojoGenerateConfigBuilder setQo(Boolean qo) {
        this.qo = qo;
        return this;
    }

    public PojoGenerateConfigBuilder setFo(Boolean fo) {
        this.fo = fo;
        return this;
    }

    public PojoGenerateConfigBuilder setSo(Boolean so) {
        this.so = so;
        return this;
    }

    public PojoGenerateConfigBuilder setTo(Boolean to) {
        this.to = to;
        return this;
    }

    public PojoGenerateConfigBuilder setVoSuperClass(String voSuperClass) {
        this.voSuperClass = voSuperClass;
        return this;
    }

    public PojoGenerateConfigBuilder setQoSuperClass(String qoSuperClass) {
        this.qoSuperClass = qoSuperClass;
        return this;
    }

    public PojoGenerateConfigBuilder setFoSuperClass(String foSuperClass) {
        this.foSuperClass = foSuperClass;
        return this;
    }

    public PojoGenerateConfigBuilder setSoSuperClass(String soSuperClass) {
        this.soSuperClass = soSuperClass;
        return this;
    }

    public PojoGenerateConfigBuilder setToSuperClass(String toSuperClass) {
        this.toSuperClass = toSuperClass;
        return this;
    }

    public PojoGenerateConfigBuilder setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public PojoGenerateConfigBuilder setUseLombok(Boolean useLombok) {
        this.useLombok = useLombok;
        return this;
    }

    public PojoGenerateConfigBuilder setGenerateConfig(GenerateConfig generateConfig) {
        this.generateConfig = generateConfig;
        return this;
    }

    public PojoGenerateConfig build() {
        return new PojoGenerateConfig(generateConfig,vo, qo, fo, so, to, voSuperClass, qoSuperClass, foSuperClass, soSuperClass, toSuperClass, packageName, useLombok);
    }
}
