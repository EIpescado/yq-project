package pers.yurwisher.clockwerk.behavioral.memento;

/**
 * @author yq
 * @date 2019/09/24 11:20
 * @description 备忘录模式 测试
 * @since V1.0.0
 */
public class MementoTest {

    private static int index = -1;
    private static MementoCaretaker mc = new MementoCaretaker();

    public static void main(String[] args) {
        Chess chess = new Chess("车", 1, 1);
        play(chess);
        chess.setY(4);
        play(chess);
        chess.setX(5);
        play(chess);
        undo(chess, index);
        undo(chess, index);
        redo(chess, index);
        redo(chess, index);
    }

    /**
     * 下棋，同时保存备忘录
     */
    private static void play(Chess chess) {
        mc.addMemento(chess.save());
        index++;
        chess.show();
    }

    /**
     * 悔棋，撤销到上一个备忘录
     */
    private static void undo(Chess chess, int i) {
        System.out.println("******悔棋******");
        index--;
        chess.restore(mc.getMemento(i - 1));
        chess.show();
    }

    /**
     * 撤销悔棋，恢复到下一个备忘录
     */
    private static void redo(Chess chess, int i) {
        System.out.println("******撤销悔棋******");
        index++;
        chess.restore(mc.getMemento(i + 1));
        chess.show();
    }

}
