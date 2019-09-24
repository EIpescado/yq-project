package pers.yurwisher.clockwerk.creational.abstractfactory;

import pers.yurwisher.clockwerk.creational.factory.color.Color;
import pers.yurwisher.clockwerk.creational.factory.shape.Shape;

/**
 * 抽象工厂模式测试
 */
public class AbstractFactoryTest {

    public static void testAbstractFactory(){
        //获取形状工厂
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");
        Shape shape = shapeFactory.getShape("Square");
        shape.draw();
        //获取形状工厂
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");
        Color color = colorFactory.getColor("Red");
        color.fill();
    }

    public static void main(String[] args) {
        testAbstractFactory();
    }
}
