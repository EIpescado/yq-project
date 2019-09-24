package pers.yurwisher.clockwerk.behavioral.observer;

/**
 * @author yq
 * @date 2019/09/24 12:08
 * @description 歌手抽象
 * @since V1.0.0
 */
public interface Singer {

    /**
     * 注册观察者
     * @param observes 新粉
     */
    void registerObserver(FanObserver... observes);

    /**
     * 通告所有粉丝我发新歌了
     */
    void notifyObserversIHavePublishNewSong(String songName);
}
