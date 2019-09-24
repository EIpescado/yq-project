package pers.yurwisher.clockwerk.behavioral.chainofresponsibility;

/**
 * @author yq
 * @date 2019/09/23 14:50
 * @description 控制台
 * @since V1.0.0
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
