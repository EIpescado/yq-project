package pers.yurwisher.clockwerk.behavioral.observer;

/**
 * @author yq
 * @date 2019/09/24 13:42
 * @description 狂热的粉丝
 * @since V1.0.0
 */
public class CrazyFanObserver implements FanObserver {
    @Override
    public void shareMoment(String songName) {
        System.out.println("狂热粉丝: 一次买了1000张" + songName);
    }
}
