package pers.yurwisher.clockwerk.behavioral.template;

/**
 * @author yq
 * @date 2019/09/24 14:46
 * @description 程序员
 * @since V1.0.0
 */
public abstract class Programmer {
    /**
     * 吃
     */
    public abstract void eat();

    /**
     * 编码
     */
    public abstract void coding();

    /**
     * 睡觉
     */
    public abstract void sleep();

    public final void spendDay(){
        eat();
        coding();
        sleep();
    }
}
