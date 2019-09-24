package pers.yurwisher.clockwerk.structural.adapter;

/**
 * @author yq
 * @date 2019/09/20 09:22
 * @description TF卡实例
 * @since V1.0.0
 */
public class TFCardImpl implements TFCard {
    private StringBuilder sb = new StringBuilder();
    @Override
    public String read() {
        return sb.toString();
    }

    @Override
    public void write(String msg) {
        sb.append(msg).append(System.currentTimeMillis());
    }
}
