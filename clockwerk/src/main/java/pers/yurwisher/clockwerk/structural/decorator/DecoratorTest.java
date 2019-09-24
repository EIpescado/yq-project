package pers.yurwisher.clockwerk.structural.decorator;

/**
 * @author yq
 * @date 2019/09/20 14:40
 * @description 装饰器模式测试
 * @since V1.0.0
 */
public class DecoratorTest {

    public static void main(String[] args) {
        Hero hero = new Pudge();
        hero.kill();
        System.out.println();
        CebHeroDecorator ceb = new CebHeroDecorator(hero);
        ceb.kill();
        System.out.println();
        NoChillHeroDecorator nochill = new NoChillHeroDecorator(hero);
        nochill.kill();
    }
}
