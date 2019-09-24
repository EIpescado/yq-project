package pers.yurwisher.clockwerk.behavioral.visitor;

/**
 * @author yq
 * @date 2019/09/24 15:12
 * @description 访问者模式 测试
 * @since V1.0.0
 */
public class VisitorTest {

    public static void main(String[] args) {
        ComputerPartVisitor visitor = new ComputerPartVisitorImpl();
        ComputerPart computer = new Computer();
        computer.accept(visitor);
    }
}
