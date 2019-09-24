package pers.yurwisher.clockwerk.structural.bridge.pen;

import pers.yurwisher.clockwerk.structural.bridge.color.Color;

/**
 * @author yq
 * @date 2019/09/20 10:18
 * @description 笔
 * @since V1.0.0
 */
public abstract class Pen {

    protected Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * 画
     */
    public abstract void draw(String name);
}
