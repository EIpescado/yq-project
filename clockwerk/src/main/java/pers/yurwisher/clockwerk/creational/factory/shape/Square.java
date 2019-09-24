package pers.yurwisher.clockwerk.creational.factory.shape;

/**
 * @author yq
 * @date 2019/09/18 15:53
 * @description 正方形
 * @since V1.0.0
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square");
    }
}
