package pers.yurwisher.clockwerk.behavioral.template;

/**
 * @author yq
 * @date 2019/09/24 14:48
 * @description c程序员
 * @since V1.0.0
 */
public class CProgrammer extends Programmer {
    @Override
    public void eat() {
        System.out.println("c程序员吃牛肉面");
    }

    @Override
    public void coding() {
        System.out.println("c coding");
    }

    @Override
    public void sleep() {
        System.out.println("c程序员睡觉");
    }
}
