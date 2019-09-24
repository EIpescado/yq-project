package pers.yurwisher.clockwerk.behavioral.template;

/**
 * @author yq
 * @date 2019/09/24 14:48
 * @description
 * @since V1.0.0
 */
public class JavaProgrammer extends Programmer {
    @Override
    public void eat() {
        System.out.println("java程序员吃热干面");
    }

    @Override
    public void coding() {
        System.out.println("java coding");
    }

    @Override
    public void sleep() {
        System.out.println("java程序员睡觉");
    }
}
