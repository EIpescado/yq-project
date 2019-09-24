package pers.yurwisher.clockwerk.behavioral.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yq
 * @date 2019/09/24 12:08
 * @description 周杰伦
 * @since V1.0.0
 */
public class JayChou implements Singer {

    private List<FanObserver> fans = new ArrayList<>();

    @Override
    public void registerObserver(FanObserver... observe) {
        Collections.addAll(fans,observe);
    }

    @Override
    public void notifyObserversIHavePublishNewSong(String songName) {
        System.out.println("5,4,3,2,1 ! hello 大家好,我是周杰伦,下面带来我的新歌 '" + songName + "'");
        fans.forEach(fan -> fan.shareMoment(songName));
    }
}
