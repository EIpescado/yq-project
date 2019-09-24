package pers.yurwisher.clockwerk.behavioral.observer;

/**
 * @author yq
 * @date 2019/09/24 13:40
 * @description 观察者模式测试
 * @since V1.0.0
 */
public class ObserverTest {

    public static void main(String[] args) {
        Singer singer = new JayChou();
        FanObserver crazy = new CrazyFanObserver();
        FanObserver normal = new NormalFanObserver();
        singer.registerObserver(crazy,normal);
        singer.notifyObserversIHavePublishNewSong("不爱我就拉倒");
        singer.notifyObserversIHavePublishNewSong("说好不哭");
    }
}
