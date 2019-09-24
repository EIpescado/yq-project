package pers.yurwisher.clockwerk.creational.builder.item;

/**
 * @author yq
 * @date 2019/09/19 09:34
 * @description 可口可乐
 * @since V1.0.0
 */
public class CocaCola extends AbstractColdDrink{
    @Override
    public String name() {
        return "Coca Cola";
    }

    @Override
    public double price() {
        return 30.0;
    }
}
