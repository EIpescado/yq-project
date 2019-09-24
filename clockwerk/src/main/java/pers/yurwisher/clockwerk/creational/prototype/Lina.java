package pers.yurwisher.clockwerk.creational.prototype;

/**
 * @author yq
 * @date 2019/09/19 11:06
 * @description 火女
 * @since V1.0.0
 */
public class Lina extends AbstractHero{

    public Lina() {
        super.name = "Lina";
    }

    @Override
    public void skill() {
        System.out.println("Lina skill one,dragon slave");
    }
}
