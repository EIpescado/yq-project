package pers.yurwisher.clockwerk.behavioral.strategy;

/**
 * @author yq
 * @date 2019/09/16 16:15
 * @description 策略
 * @since V1.0.0
 */
public interface Strategy {

    /**
     * 操作
     * @param num1 参数
     * @param num2 参数
     * @return 结果
     */
    int doOperation(int num1, int num2);
}
