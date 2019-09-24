package pers.yurwisher.clockwerk.behavioral.memento;

/**
 * @author yq
 * @date 2019/09/24 11:16
 * @description 棋子备忘录 Memento
 * @since V1.0.0
 */
public class ChessMemento {

    private int x;
    private int y;

    public ChessMemento(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
