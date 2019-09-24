package pers.yurwisher.clockwerk.behavioral.memento;

/**
 * @author yq
 * @date 2019/09/24 11:16
 * @description 棋子  原发器Originator
 * @since V1.0.0
 */
public class Chess {

    private String name;
    private int x;
    private int y;

    public Chess(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    //保存状态
    public ChessMemento save() {
        return new ChessMemento(this.x, this.y);
    }

    //恢复状态
    public void restore(ChessMemento memento) {
        this.x = memento.getX();
        this.y = memento.getY();
    }

    public void show() {
        System.out.println(String.format("棋子<%s>：当前位置为：<%d, %d>", this.getName(), this.getX(), this.getY()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
