package pers.yurwisher.clockwerk.behavioral.observer;

/**
 * @author yq
 * @date 2019/09/24 12:09
 * @description 粉丝观察者
 * @since V1.0.0
 */
public interface FanObserver {

    /**
     * 发送朋友圈
     * @param songName 歌曲名称
     */
    void shareMoment(String songName);
}
