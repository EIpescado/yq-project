package pers.yurwisher.clockwerk.behavioral.chainofresponsibility;

/**
 * @author yq
 * @date 2019/09/23 14:47
 * @description 抽象logger
 * @since V1.0.0
 */
public abstract class AbstractLogger {

    /**
     * 日志等级
     */
    protected int level;

    /**
     * 责任链中的下一个元素
     */
    protected AbstractLogger nextLogger;

    /**
     * 设置下一个元素
     * @param nextLogger 次级logger
     */
    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    /**
     * 记录信息
     * @param level 级别
     * @param message 消息
     */
    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    /**
     * 写日志
     * @param message 日志记录
     */
    abstract protected void write(String message);
}
