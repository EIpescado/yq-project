package pers.yurwisher.clockwerk.structural.flyweight;

/**
 * @author yq
 * @date 2019/09/20 17:50
 * @description 工人
 * @since V1.0.0
 */
public interface Worker {

    /**
     * 制作物件
     * @param things 物品
     */
    void make(String things);
}
