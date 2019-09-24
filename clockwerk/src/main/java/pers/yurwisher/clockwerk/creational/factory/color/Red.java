package pers.yurwisher.clockwerk.creational.factory.color;

/**
 * @author yq
 * @date 2019/09/18 16:17
 * @description 红色
 * @since V1.0.0
 */
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("fill red");
    }
}
