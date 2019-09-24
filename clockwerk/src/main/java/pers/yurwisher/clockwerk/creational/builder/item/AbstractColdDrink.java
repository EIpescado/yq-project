package pers.yurwisher.clockwerk.creational.builder.item;

import pers.yurwisher.clockwerk.creational.builder.packing.Bottle;
import pers.yurwisher.clockwerk.creational.builder.packing.Packing;

/**
 * @author yq
 * @date 2019/09/19 09:26
 * @description 冷饮
 * @since V1.0.0
 */
public abstract class AbstractColdDrink implements Item {

    @Override
    public Packing packing() {
        //冷饮均为瓶装
        return new Bottle();
    }
}
