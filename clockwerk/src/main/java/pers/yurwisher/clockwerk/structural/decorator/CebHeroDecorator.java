package pers.yurwisher.clockwerk.structural.decorator;

/**
 * @author yq
 * @date 2019/09/20 14:36
 * @description ceb嘲讽
 * @since V1.0.0
 */
public class CebHeroDecorator extends AbstractHeroDecorator {

    public CebHeroDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public void sneer() {
        System.out.print("then say : ceeeeeeeeeeeeeeeeeeeeeeeeeeeeeeb!");
    }

}
