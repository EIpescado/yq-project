package pers.yurwisher.clockwerk.creational.builder.item;

/**
 * @author yq
 * @date 2019/09/19 09:33
 * @description 肌肉汉堡
 * @since V1.0.0
 */
public class ChickenBurger extends AbstractBurger {
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public double price() {
        return 50.5;
    }
}
