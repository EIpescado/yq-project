package pers.yurwisher.clockwerk.structural.adapter;

/**
 * @author yq
 * @date 2019/09/20 09:13
 * @description SD卡 旧
 * @since V1.0.0
 */
public interface SDCard {

    /**
     *  读卡
     */
    String read();

    /**
     * 写卡
     * @param msg  写入的内容
     */
    void write(String msg);
}
