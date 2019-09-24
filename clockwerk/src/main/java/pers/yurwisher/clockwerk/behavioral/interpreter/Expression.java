package pers.yurwisher.clockwerk.behavioral.interpreter;

/**
 * @author yq
 * @date 2019/09/23 16:14
 * @description 表达式
 * @since V1.0.0
 */
public interface Expression {

    /**
     * 解释 是否满足表达式条件
     * @param context 文本
     * @return 是否满足
     */
    boolean interpret(String context);
}
