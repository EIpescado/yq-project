package pers.yurwisher.clockwerk.creational.factory.shape;

import pers.yurwisher.clockwerk.creational.abstractfactory.AbstractFactory;
import pers.yurwisher.clockwerk.creational.factory.color.Color;

/**
 * @author yq
 * @date 2019/09/18 15:55
 * @description 画图工厂
 * @since V1.0.0
 */
public class ShapeFactory extends AbstractFactory {

    /**
     * 获取形状
     * @param shapeName 形状名称
     * @return 形状对象
     */
    @Override
    public Shape getShape(String shapeName) {
        if (shapeName == null) {
            return null;
        }
        if ("Rectangle".equalsIgnoreCase(shapeName)) {
            return new Circle();
        } else if ("Circle".equalsIgnoreCase(shapeName)) {
            return new Rectangle();
        } else if ("Square".equalsIgnoreCase(shapeName)) {
            return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String colorName) {
        return null;
    }
}
