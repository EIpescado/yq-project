package pers.yurwisher.clockwerk.structural.adapter;

/**
 * @author yq
 * @date 2019/09/20 09:16
 * @description 电脑.旧只读SD卡,新需要能读TF卡
 * @since V1.0.0
 */
public interface Computer {

    /**
     * 电脑读取SD卡
     * @param sdCard sd卡
     * @return sd卡内内容
     */
    String readSDCard(SDCard sdCard);
}
