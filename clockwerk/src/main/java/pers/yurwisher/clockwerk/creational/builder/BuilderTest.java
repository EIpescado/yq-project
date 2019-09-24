package pers.yurwisher.clockwerk.creational.builder;

/**
 * 建造模式测试
 * @author yq 2019年9月19日10:06:51
 */
public class BuilderTest {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("veg meal...");
        vegMeal.show();
        System.out.println("Total Cost: " +vegMeal.totalCost());

        Meal nonVegMeal = mealBuilder.prepareChickenMeal();
        System.out.println("chicken meal...");
        nonVegMeal.show();
        System.out.println("Total Cost: " +nonVegMeal.totalCost());
    }
}
