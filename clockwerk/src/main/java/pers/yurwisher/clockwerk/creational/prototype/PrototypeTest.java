package pers.yurwisher.clockwerk.creational.prototype;

/**
 * @author yq
 * @date 2019/09/19 11:11
 * @description 原型模式测试
 * @since V1.0.0
 */
public class PrototypeTest {

    public static void main(String[] args) {
        HeroCache.loadCache();
        AbstractHero pudge = HeroCache.getShape("1");
        System.out.println(pudge.getName() + "_"+ pudge.toString());
        AbstractHero lina = HeroCache.getShape("2");
        System.out.println(lina.getName() + "_"+ lina.toString());
    }
}
