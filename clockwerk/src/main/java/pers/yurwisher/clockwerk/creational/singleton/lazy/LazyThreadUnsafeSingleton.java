package pers.yurwisher.clockwerk.creational.singleton.lazy;

/**
 * @author yq
 * @date 2019/09/18 17:07
 * @description 懒汉线程不安全单例
 * @since V1.0.0
 */
public class LazyThreadUnsafeSingleton {

    private static LazyThreadUnsafeSingleton instance;
    private LazyThreadUnsafeSingleton (){}

    public static LazyThreadUnsafeSingleton getInstance() {
        if (instance == null) {
            instance = new LazyThreadUnsafeSingleton();
        }
        return instance;
    }
}
