package pers.yurwisher.clockwerk.creational.builder.packing;

/**
 * @author yq
 * @date 2019/09/19 09:24
 * @description 纸质包装
 * @since V1.0.0
 */
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}
