package pers.yurwisher.clockwerk.creational.singleton.lazy;

/**
 * @author yq
 * @date 2019/09/18 17:23
 * @description 登记式/静态内部类 线程安全,适用于静态域的情况
 * @since V1.0.0
 */
public class LazyThreadSafeHolderSingleton {

    private static class LazyThreadSafeHolderSingletonHolder{
        private static final LazyThreadSafeHolderSingleton INSTANCE = new  LazyThreadSafeHolderSingleton();
    }
    private LazyThreadSafeHolderSingleton(){}

    public static  LazyThreadSafeHolderSingleton getInstance(){
        return LazyThreadSafeHolderSingletonHolder.INSTANCE;
    }
}
