package pers.yurwisher.clockwerk.creational.builder.item;

/**
 * @author yq
 * @date 2019/09/19 09:33
 * @description 素食汉堡
 * @since V1.0.0
 */
public class VegBurger extends AbstractBurger {
    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public double price() {
        return 25.0;
    }
}
