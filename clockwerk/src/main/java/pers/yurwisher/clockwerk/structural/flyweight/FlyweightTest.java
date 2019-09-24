package pers.yurwisher.clockwerk.structural.flyweight;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author yq
 * @date 2019/09/20 17:57
 * @description 享元模式测试
 * @since V1.0.0
 */
public class FlyweightTest {
    private static final String[] THING_ARRAY =
            { "锅", "碗", "铲", "刀", "叉" };

    public static void main(String[] args) {
        WorkerFactory factory = new WorkerFactory();
        for (int i = 0; i < 20; ++i) {
            Worker worker = factory.getWorker(getRandomNo());
            worker.make(getRandomThings());
        }
    }

    private static long getRandomNo() {
        return ThreadLocalRandom.current().nextLong(5);
    }

    private static String getRandomThings() {
        return THING_ARRAY[(int)(Math.random()*THING_ARRAY.length)];
    }
}
