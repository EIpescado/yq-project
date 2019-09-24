package pers.yurwisher.clockwerk.creational.factory.shape;

/**
 * @author yq
 * @date 2019/09/18 15:53
 * @description 圆形
 * @since V1.0.0
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle");
    }
}
