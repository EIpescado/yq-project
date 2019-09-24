package pers.yurwisher.clockwerk.behavioral.chainofresponsibility;

/**
 * @author yq
 * @date 2019/09/23 14:55
 * @description 责任链模式测试
 * @since V1.0.0
 */
public class ChainOfResponsibility {

    private static int DEBUG = 1;
    private static int INFO = 2;
    private static int ERROR = 3;

    private static AbstractLogger getChainOfLoggers(){

        AbstractLogger errorLogger = new ErrorLogger(ERROR);
        AbstractLogger fileLogger = new FileLogger(INFO);
        AbstractLogger consoleLogger = new ConsoleLogger(DEBUG);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }



    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(INFO, "This is an information.");
        System.out.println();

        loggerChain.logMessage(DEBUG,
                "This is a debug level information.");
        System.out.println();

        loggerChain.logMessage(ERROR,
                "This is an error information.");
        System.out.println();
    }
}
