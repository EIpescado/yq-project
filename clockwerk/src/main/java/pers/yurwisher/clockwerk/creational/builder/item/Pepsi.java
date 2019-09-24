package pers.yurwisher.clockwerk.creational.builder.item;

/**
 * @author yq
 * @date 2019/09/19 09:34
 * @description 百事可乐
 * @since V1.0.0
 */
public class Pepsi extends AbstractColdDrink{
    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public double price() {
        return 35.0;
    }
}
