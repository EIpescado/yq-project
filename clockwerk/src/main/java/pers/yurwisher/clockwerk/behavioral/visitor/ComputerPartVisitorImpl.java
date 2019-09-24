package pers.yurwisher.clockwerk.behavioral.visitor;

/**
 * @author yq
 * @date 2019/09/24 15:11
 * @description 默认配件访问者
 * @since V1.0.0
 */
public class ComputerPartVisitorImpl implements ComputerPartVisitor {
    @Override
    public void visit(ComputerPart part) {
        System.out.println("配件:" + part.name() + "动了一下");
    }
}
