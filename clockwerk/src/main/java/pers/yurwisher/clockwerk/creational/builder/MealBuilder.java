package pers.yurwisher.clockwerk.creational.builder;

import pers.yurwisher.clockwerk.creational.builder.item.ChickenBurger;
import pers.yurwisher.clockwerk.creational.builder.item.CocaCola;
import pers.yurwisher.clockwerk.creational.builder.item.Pepsi;
import pers.yurwisher.clockwerk.creational.builder.item.VegBurger;

/**
 * @author yq
 * @date 2019/09/19 10:04
 * @description 柜员,即套餐构建者
 * @since V1.0.0
 */
public class MealBuilder {

    /**
     * 素食汉堡套餐
     */
    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new CocaCola());
        return meal;
    }

    /**
     * 鸡肉汉堡套餐
     */
    public Meal prepareChickenMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
