package pers.yurwisher.clockwerk.creational.singleton.lazy;

/**
 * @author yq
 * @date 2019/09/18 17:21
 * @description 双检锁/双重校验锁（DCL，即 double-checked locking）单例,线程安全
 *  实例域需要延迟初始化时使用
 * @since V1.0.0
 */
public class LazyDCLThreadSafeSingleton {
    private volatile static LazyDCLThreadSafeSingleton instance;

    private LazyDCLThreadSafeSingleton() {
    }

    public static LazyDCLThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (LazyDCLThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new LazyDCLThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
