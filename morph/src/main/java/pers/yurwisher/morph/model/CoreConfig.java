package pers.yurwisher.morph.model;

/**
 * @author yq
 * @date 2019/06/17 14:02
 * @description
 * @since V1.0.0
 */
public class CoreConfig {

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

    private Boolean useLombok;

    public CoreConfig(Boolean vo, Boolean qo, Boolean fo, Boolean so, Boolean to, String voSuperClass, String qoSuperClass, String foSuperClass, String soSuperClass, String toSuperClass, String serviceSuperClass,String serviceImplSuperClass, String controllerSuperClass, Boolean useLombok) {
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
        this.serviceSuperClass = serviceSuperClass;
        this.serviceImplSuperClass = serviceImplSuperClass;
        this.controllerSuperClass = controllerSuperClass;
        this.useLombok = useLombok;
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

    public String getServiceSuperClass() {
        return serviceSuperClass;
    }

    public void setServiceSuperClass(String serviceSuperClass) {
        this.serviceSuperClass = serviceSuperClass;
    }

    public String getControllerSuperClass() {
        return controllerSuperClass;
    }

    public void setControllerSuperClass(String controllerSuperClass) {
        this.controllerSuperClass = controllerSuperClass;
    }

    public Boolean getUseLombok() {
        return useLombok;
    }

    public void setUseLombok(Boolean useLombok) {
        this.useLombok = useLombok;
    }

    public String getServiceImplSuperClass() {
        return serviceImplSuperClass;
    }

    public void setServiceImplSuperClass(String serviceImplSuperClass) {
        this.serviceImplSuperClass = serviceImplSuperClass;
    }
}
