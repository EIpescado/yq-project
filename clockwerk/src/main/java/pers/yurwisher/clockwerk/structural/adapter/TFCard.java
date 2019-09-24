package pers.yurwisher.clockwerk.structural.adapter;

/**
 * @author yq
 * @date 2019/09/20 09:21
 * @description TF卡
 * @since V1.0.0
 */
public interface TFCard {

    /**
     * 读TF卡
     */
    String read();

    /**
     * 写TF卡
     * @param msg msg
     */
    void write(String msg);
}
