package pers.yurwisher.clockwerk.creational.factory.color;

import pers.yurwisher.clockwerk.creational.abstractfactory.AbstractFactory;
import pers.yurwisher.clockwerk.creational.factory.shape.Shape;

/**
 * @author yq
 * @date 2019/09/18 15:55
 * @description 颜色工厂
 * @since V1.0.0
 */
public class ColorFactory extends AbstractFactory {

    /**
     * 获取形状
     * @param shapeName 形状名称
     * @return 形状对象
     */
    @Override
    public Shape getShape(String shapeName) {
        return null;
    }

    @Override
    public Color getColor(String colorName) {
        if (colorName == null) {
            return null;
        }
        if ("Red".equalsIgnoreCase(colorName)) {
            return new Red();
        } else if ("Green".equalsIgnoreCase(colorName)) {
            return new Green();
        } else if ("Blue".equalsIgnoreCase(colorName)) {
            return new Blue();
        }
        return null;
    }
}
