package pers.yurwisher.clockwerk.creational.builder;

import pers.yurwisher.clockwerk.creational.builder.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yq
 * @date 2019/09/19 09:38
 * @description 套餐
 * @since V1.0.0
 */
public class Meal {

    private List<Item> items = new ArrayList<>();

    /**
     * 添加商品
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * 商品总价
     */
    public double totalCost() {
        double cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    public void show(){
        for (Item item : items) {
            System.out.print("Item : "+item.name());
            System.out.print(", Packing : "+item.packing().pack());
            System.out.println(", Price : "+item.price());
        }
    }
}
