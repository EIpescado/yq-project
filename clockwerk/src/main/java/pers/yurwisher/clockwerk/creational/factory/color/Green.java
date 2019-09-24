package pers.yurwisher.clockwerk.creational.factory.color;

/**
 * @author yq
 * @date 2019/09/18 16:17
 * @description 绿色
 * @since V1.0.0
 */
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("fill green");
    }
}
