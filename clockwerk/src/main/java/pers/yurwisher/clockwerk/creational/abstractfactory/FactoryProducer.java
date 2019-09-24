package pers.yurwisher.clockwerk.creational.abstractfactory;

import pers.yurwisher.clockwerk.creational.factory.color.ColorFactory;
import pers.yurwisher.clockwerk.creational.factory.shape.ShapeFactory;

/**
 * @author yq
 * @date 2019/09/18 16:34
 * @description 工厂创造器/生成器
 * @since V1.0.0
 */
public class FactoryProducer {

    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}
