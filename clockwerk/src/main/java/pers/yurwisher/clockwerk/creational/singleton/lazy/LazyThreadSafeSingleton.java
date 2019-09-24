package pers.yurwisher.clockwerk.creational.singleton.lazy;

/**
 * @author yq
 * @date 2019/09/18 17:07
 * @description 懒汉线程安全单例,效率低,99%的情况下不需要同步
 * @since V1.0.0
 */
public class LazyThreadSafeSingleton {

    private static LazyThreadSafeSingleton instance;
    private LazyThreadSafeSingleton(){}

    public static synchronized LazyThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new LazyThreadSafeSingleton();
        }
        return instance;
    }
}
