package pers.yurwisher.clockwerk.structural.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author yq
 * @date 2019/09/23 14:14
 * @description JDK动态代理测试 ,只能基于接口
 * @since V1.0.0
 */
public class JDKProxyTest {
    public static void main(String[] args) {
        //真实对象
        Subject realSubject =  new RealSubject();

        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(realSubject);
        //代理对象
        Subject proxyClass = (Subject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Subject.class}, myInvocationHandler);

        proxyClass.sellBooks();

        proxyClass.speak();
    }
}
