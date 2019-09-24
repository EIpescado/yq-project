package pers.yurwisher.clockwerk.structural.adapter;

/**
 * @author yq
 * @date 2019/09/20 09:14
 * @description SD卡实现
 * @since V1.0.0
 */
public class SDCardImpl implements SDCard {

    private StringBuilder sb = new StringBuilder();

    @Override
    public String read() {
        return sb.toString();
    }

    @Override
    public void write(String msg) {
        sb.append(msg);
    }
}
