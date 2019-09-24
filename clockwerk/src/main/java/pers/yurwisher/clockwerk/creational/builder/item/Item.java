package pers.yurwisher.clockwerk.creational.builder.item;

import pers.yurwisher.clockwerk.creational.builder.packing.Packing;

/**
 * @author yq
 * @date 2019/09/19 09:21
 * @description 商品
 * @since V1.0.0
 */
public interface Item {
    /**
     * 商品名称
     */
    String name();

    /**
     * 单价
     */
    double price();

    /**
     * 包装
     */
    Packing packing();
}
