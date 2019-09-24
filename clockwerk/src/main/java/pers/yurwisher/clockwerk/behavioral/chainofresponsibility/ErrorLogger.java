package pers.yurwisher.clockwerk.behavioral.chainofresponsibility;

/**
 * @author yq
 * @date 2019/09/23 14:53
 * @description 错误日志
 * @since V1.0.0
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }
    @Override
    protected void write(String message) {
        System.out.println("ERROR ::Logger: " + message);
    }
}
