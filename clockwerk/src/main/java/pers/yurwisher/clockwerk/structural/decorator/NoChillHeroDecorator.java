package pers.yurwisher.clockwerk.structural.decorator;

/**
 * @author yq
 * @date 2019/09/20 14:36
 * @description no chill嘲讽
 * @since V1.0.0
 */
public class NoChillHeroDecorator extends AbstractHeroDecorator {

    public NoChillHeroDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public void sneer() {
        System.out.print("then say : this guy had no chill!");
    }

}
