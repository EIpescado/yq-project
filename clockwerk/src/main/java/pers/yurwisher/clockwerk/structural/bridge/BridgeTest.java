package pers.yurwisher.clockwerk.structural.bridge;

import pers.yurwisher.clockwerk.structural.bridge.color.Red;
import pers.yurwisher.clockwerk.structural.bridge.pen.Pen;
import pers.yurwisher.clockwerk.structural.bridge.pen.SPen;

/**
 * @author yq
 * @date 2019/09/20 10:31
 * @description 桥接模式测试
 * @since V1.0.0
 */
public class BridgeTest {

    public static void main(String[] args) {
        Pen pen = new SPen();
        pen.setColor(new Red());
        pen.draw("国旗");
    }
}
