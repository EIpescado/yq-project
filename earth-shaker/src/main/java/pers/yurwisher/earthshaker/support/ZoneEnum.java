package pers.yurwisher.earthshaker.support;

/**
 * @author yq
 * @date 2019/08/08 16:47
 * @description 七牛文件机房zone
 * @since V1.0.0
 */
public enum ZoneEnum {

    zone0("华东"),
    zone1("华北"),
    zone2("华南"),
    zoneNa0("北美"),
    zoneAs0("东南亚"),
    ;

    private String zone;

    ZoneEnum(String zone) {
        this.zone = zone;
    }

    public String getZone() {
        return zone;
    }
}
