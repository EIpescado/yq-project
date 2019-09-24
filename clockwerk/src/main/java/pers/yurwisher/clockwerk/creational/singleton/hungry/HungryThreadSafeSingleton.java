package pers.yurwisher.clockwerk.creational.singleton.hungry;

/**
 * @author yq
 * @date 2019/09/18 17:14
 * @description  饿汉式线程安全单例
 * @since V1.0.0
 */
public class HungryThreadSafeSingleton {

    private static HungryThreadSafeSingleton instance = new HungryThreadSafeSingleton();

    private HungryThreadSafeSingleton(){}

    public static HungryThreadSafeSingleton getInstance(){
        return instance;
    }
}
