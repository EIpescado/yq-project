package pers.yurwisher.clockwerk.structural.proxy;

/**
 * @author yq
 * @date 2019/09/20 19:02
 * @description 代理模式测试
 * @since V1.0.0
 */
public class ProxyTest {

    public static void main(String[] args) {
        Image image = new ImageProxy("test.jpg");
        image.display();
        System.out.println();
        //本次无需从磁盘加载
        image.display();
    }
}
