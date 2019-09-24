package pers.yurwisher.clockwerk.behavioral.memento;

import java.util.ArrayList;

/**
 * @author yq
 * @date 2019/09/24 11:18
 * @description 备忘录负责人 Caretaker
 * @since V1.0.0
 */
public class MementoCaretaker {


    /**
     * 存储备忘录集合
     */
    private ArrayList<ChessMemento> mementoList = new ArrayList<>();

    /**
     * 获取备忘录
     * @param i 指定序号
     */
    public ChessMemento getMemento(int i) {
        return mementoList.get(i);
    }

    /**
     * 保存备忘录
     * @param memento 备忘录
     */
    public void addMemento(ChessMemento memento) {
        mementoList.add(memento);
    }
}
