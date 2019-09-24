package pers.yurwisher.clockwerk.structural.adapter;

/**
 * @author yq
 * @date 2019/09/20 09:17
 * @description Think pad
 * @since V1.0.0
 */
public class ThinkpadComputer implements Computer {
    @Override
    public String readSDCard(SDCard sdCard) {
        return sdCard.read();
    }
}
