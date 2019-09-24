package pers.yurwisher.clockwerk.creational.prototype;

import java.util.Hashtable;

/**
 * @author yq
 * @date 2019/09/19 11:08
 * @description 英雄缓存
 * @since V1.0.0
 */
public class HeroCache {

    private static Hashtable<String, AbstractHero> heroMap = new Hashtable<>();

    public static AbstractHero getShape(String shapeId) {
        AbstractHero cacheHero = heroMap.get(shapeId);
        return (AbstractHero) cacheHero.clone();
    }

    /**
     * 加载缓存
     */
    public static void loadCache() {
        Pudge pudge = new Pudge();
        pudge.setId("1");
        heroMap.put(pudge.getId(), pudge);
        System.out.println("原始pudge对象"+ pudge.toString());

        Lina lina = new Lina();
        lina.setId("2");
        heroMap.put(lina.getId(), lina);
        System.out.println("原始lina对象"+ lina.toString());
    }
}
