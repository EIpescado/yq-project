package pers.yurwisher.clockwerk.structural.bridge.pen;

/**
 * @author yq
 * @date 2019/09/20 10:19
 * @description 小号笔
 * @since V1.0.0
 */
public class SPen extends Pen {
    @Override
    public void draw(String name) {
        System.out.println("小号笔画: " + name + ",颜色: " + super.color.bepaint());
    }
}
