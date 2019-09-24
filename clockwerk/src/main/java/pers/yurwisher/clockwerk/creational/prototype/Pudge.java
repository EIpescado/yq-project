package pers.yurwisher.clockwerk.creational.prototype;

/**
 * @author yq
 * @date 2019/09/19 11:03
 * @description 屠夫
 * @since V1.0.0
 */
public class Pudge extends AbstractHero {

    public Pudge() {
       super.name = "Pudge";
    }

    @Override
    public void skill() {
        System.out.println("Pudge skill one ,hook!");
    }
}
