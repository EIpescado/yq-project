package pers.yurwisher.clockwerk.creational.builder.item;

import pers.yurwisher.clockwerk.creational.builder.packing.Packing;
import pers.yurwisher.clockwerk.creational.builder.packing.Wrapper;

/**
 * @author yq
 * @date 2019/09/19 09:26
 * @description 汉堡
 * @since V1.0.0
 */
public abstract class AbstractBurger implements Item {

    @Override
    public Packing packing() {
        //汉堡均为纸质包装
        return new Wrapper();
    }
}
