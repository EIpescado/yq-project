package pers.yurwisher.clockwerk.structural.flyweight;

/**
 * @author yq
 * @date 2019/09/20 18:00
 * @description 物品
 * @since V1.0.0
 */
public class Thing {

    /**
     * 类别
     */
    private String type;

    /**
     * 颜色
     */
    private String color;

    public Thing(String type, String color) {
        this.type = type;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }
}
