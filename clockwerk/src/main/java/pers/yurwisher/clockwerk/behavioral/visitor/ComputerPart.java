package pers.yurwisher.clockwerk.behavioral.visitor;

/**
 * @author yq
 * @date 2019/09/24 15:07
 * @description 电脑配件
 * @since V1.0.0
 */
public interface ComputerPart {

    /**
     *  配件名称
     * @return 名称
     */
    String name();

    /**
     * 接收指令
     */
    void accept(ComputerPartVisitor visitor);
}
