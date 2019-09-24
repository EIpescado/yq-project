package pers.yurwisher.clockwerk.creational.factory;

import pers.yurwisher.clockwerk.creational.factory.shape.Shape;
import pers.yurwisher.clockwerk.creational.factory.shape.ShapeFactory;

/**
 * 工厂模式测试
 */
public class FactoryTest {

    public static void testFactory(){
        ShapeFactory factory = new ShapeFactory();
        Shape square = factory.getShape("Square");
        square.draw();
        Shape rectangle = factory.getShape("Rectangle");
        rectangle.draw();
        Shape circle = factory.getShape("Circle");
        circle.draw();
    }

    public static void main(String[] args) {
        testFactory();
    }
}
