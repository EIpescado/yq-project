package pers.yurwisher.clockwerk.behavioral.strategy;

/**
 * @author yq
 * @date 2019/09/16 16:17
 * @description 加法
 * @since V1.0.0
 */
public class AddStrategyImpl implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
