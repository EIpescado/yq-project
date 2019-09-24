package pers.yurwisher.clockwerk.behavioral.visitor;

/**
 * @author yq
 * @date 2019/09/24 15:08
 * @description 电脑配件访问者
 * @since V1.0.0
 */
public interface ComputerPartVisitor {

    /**
     * 访问电脑配件
     * @param part 配件
     */
    void visit(ComputerPart part);
}
