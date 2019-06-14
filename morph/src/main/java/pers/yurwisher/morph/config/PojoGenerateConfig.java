package pers.yurwisher.morph.config;

/**
 * @author yq
 * @date 2019/06/12 11:25
 * @description
 * @since V1.0.0
 */
public class PojoGenerateConfig{
    /**
     * 视图对象
     */
    private Boolean vo;
    /**
     * 查询对象
     */
    private Boolean qo;
    /**
     * 表单对象
     */
    private Boolean fo;
    /**
     * 下拉框对象
     */
    private Boolean so;
    /**
     * 列表对象
     */
    private Boolean to;

    /**
     * 对应的所有父类
     */
    private String voSuperClass;
    private String qoSuperClass;
    private String foSuperClass;
    private String soSuperClass;
    private String toSuperClass;

    /**
     * 包名
     */
    private String packageName;
    /**
     * 是否使用Lombok
     */
    private boolean useLombok;

    private GenerateConfig generateConfig;

    public PojoGenerateConfig(GenerateConfig generateConfig,Boolean vo, Boolean qo, Boolean fo, Boolean so, Boolean to, String voSuperClass, String qoSuperClass, String foSuperClass, String soSuperClass, String toSuperClass, String packageName, Boolean useLombok) {
        this.vo = vo == null ? true : vo;
        this.qo = qo == null ? true : qo;
        this.fo = fo == null ? true : fo;
        this.so = so == null ? true : so;
        this.to = to == null ? true : to;
        this.voSuperClass = voSuperClass;
        this.qoSuperClass = qoSuperClass;
        this.foSuperClass = foSuperClass;
        this.soSuperClass = soSuperClass;
        this.toSuperClass = toSuperClass;
        this.packageName = packageName;
        this.useLombok = useLombok == null ? true : useLombok;
        this.generateConfig = generateConfig;
    }

    public Boolean getVo() {
        return vo;
    }

    public void setVo(Boolean vo) {
        this.vo = vo;
    }

    public Boolean getQo() {
        return qo;
    }

    public void setQo(Boolean qo) {
        this.qo = qo;
    }

    public Boolean getFo() {
        return fo;
    }

    public void setFo(Boolean fo) {
        this.fo = fo;
    }

    public Boolean getSo() {
        return so;
    }

    public void setSo(Boolean so) {
        this.so = so;
    }

    public Boolean getTo() {
        return to;
    }

    public void setTo(Boolean to) {
        this.to = to;
    }

    public String getVoSuperClass() {
        return voSuperClass;
    }

    public void setVoSuperClass(String voSuperClass) {
        this.voSuperClass = voSuperClass;
    }

    public String getQoSuperClass() {
        return qoSuperClass;
    }

    public void setQoSuperClass(String qoSuperClass) {
        this.qoSuperClass = qoSuperClass;
    }

    public String getFoSuperClass() {
        return foSuperClass;
    }

    public void setFoSuperClass(String foSuperClass) {
        this.foSuperClass = foSuperClass;
    }

    public String getSoSuperClass() {
        return soSuperClass;
    }

    public void setSoSuperClass(String soSuperClass) {
        this.soSuperClass = soSuperClass;
    }

    public String getToSuperClass() {
        return toSuperClass;
    }

    public void setToSuperClass(String toSuperClass) {
        this.toSuperClass = toSuperClass;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isUseLombok() {
        return useLombok;
    }

    public void setUseLombok(boolean useLombok) {
        this.useLombok = useLombok;
    }

    public GenerateConfig getGenerateConfig() {
        return generateConfig;
    }

    public void setGenerateConfig(GenerateConfig generateConfig) {
        this.generateConfig = generateConfig;
    }
}
