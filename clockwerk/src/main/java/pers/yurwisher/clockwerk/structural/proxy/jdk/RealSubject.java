package pers.yurwisher.clockwerk.structural.proxy.jdk;

/**
 * @author yq
 * @date 2019/09/23 14:12
 * @description
 * @since V1.0.0
 */
public class RealSubject implements Subject {
    @Override
    public int sellBooks() {
        System.out.println("卖书");
        return 1 ;
    }

    @Override
    public String speak() {
        System.out.println("说话");
        return "张三";
    }
}
