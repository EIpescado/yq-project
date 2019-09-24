package pers.yurwisher.clockwerk.structural.bridge.pen;

/**
 * @author yq
 * @date 2019/09/20 10:19
 * @description 中号笔
 * @since V1.0.0
 */
public class MPen extends Pen {
    @Override
    public void draw(String name) {
        System.out.println("中号笔画: " + name + ",颜色: " + super.color.bepaint());
    }
}
