package pers.yurwisher.clockwerk.behavioral.chainofresponsibility;

/**
 * @author yq
 * @date 2019/09/23 14:50
 * @description 文件
 * @since V1.0.0
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File ::Logger: " + message);
    }
}
