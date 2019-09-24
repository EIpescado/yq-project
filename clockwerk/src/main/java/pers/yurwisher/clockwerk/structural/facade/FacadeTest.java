package pers.yurwisher.clockwerk.structural.facade;

/**
 * @author yq
 * @date 2019/09/20 16:25
 * @description 外观模式/门面模式测试
 * @since V1.0.0
 */
public class FacadeTest {

    public static void main(String[] args) {
        SmallFreshMeatFacade freshMeat = new SmallFreshMeatFacade();
        freshMeat.cxkDance();
        freshMeat.cxkSing();
        freshMeat.cxkRap();
        freshMeat.wyfDance();
        freshMeat.wyfSing();
        freshMeat.wyfRap();
    }
}
