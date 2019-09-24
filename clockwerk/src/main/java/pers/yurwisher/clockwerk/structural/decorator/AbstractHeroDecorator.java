package pers.yurwisher.clockwerk.structural.decorator;

/**
 * @author yq
 * @date 2019/09/20 14:33
 * @description 英雄包装,击杀后需要喊一嘲讽
 * @since V1.0.0
 */
public abstract class AbstractHeroDecorator implements Hero{

    protected Hero hero;

    public AbstractHeroDecorator(Hero hero) {
        this.hero = hero;
    }

    public abstract void sneer();

    @Override
    public void kill() {
        hero.kill();
        sneer();
    }
}
