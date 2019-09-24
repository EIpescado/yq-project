package pers.yurwisher.clockwerk.behavioral.observer;

/**
 * @author yq
 * @date 2019/09/24 13:42
 * @description 正常粉丝
 * @since V1.0.0
 */
public class NormalFanObserver implements FanObserver {

    @Override
    public void shareMoment(String songName) {
        System.out.println("正常粉丝: 买了一张 '" + songName + "' 支持一下");
    }
}
