package pers.yurwisher.clockwerk.creational.abstractfactory;

import pers.yurwisher.clockwerk.creational.factory.color.Color;
import pers.yurwisher.clockwerk.creational.factory.shape.Shape;

/**
 * @author yq
 * @date 2019/09/18 16:15
 * @description 抽象工厂
 * @since V1.0.0
 */
public abstract class AbstractFactory {
    /**
     * 根据形状名称获取形状对象
     * @param shapeName 形状名称
     * @return 形状对象
     */
    public abstract Shape getShape(String shapeName);

    /**
     * 获取颜色
     * @param colorName 颜色名称
     * @return 颜色对象
     */
    public abstract Color getColor(String colorName);
}
