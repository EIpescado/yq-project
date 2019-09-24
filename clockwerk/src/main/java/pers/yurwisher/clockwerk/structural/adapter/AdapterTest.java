package pers.yurwisher.clockwerk.structural.adapter;

/**
 * @author yq
 * @date 2019/09/20 09:19
 * @description Adapter test
 * @since V1.0.0
 */
public class AdapterTest {

    /**
     *  Target 目标SDCard  目标抽象类定义客户所需接口，可以是一个抽象类或接口，也可以是具体类
     *  Adapter 适配器 SDAdapterTF  适配器可以调用另一个接口，作为一个转换器，对Adaptee和Target进行适配，
     *          适配器类是适配器模式的核心，在对象适配器中，它通过继承Target并关联一个Adaptee对象使二者产生联系
     *  Adaptee 适配者 适配者即被适配的角色，它定义了一个已经存在的接口，这个接口需要适配，适配者类一般是一个具体类，
     *          包含了客户希望使用的业务方法，在某些情况下可能没有适配者类的源代码
     *
     *  分为: 对象适配 适配器和适配者是关联关系,本例子即为对象适配
     *        类适配   适配器与适配者之间是继承（或实现）关系
     */
    public static void main(String[] args) {
        Computer computer = new ThinkpadComputer();
        SDCard sdCard = new SDCardImpl();
        sdCard.write("this is a test of adapter");
        String msg = computer.readSDCard(sdCard);
        System.out.println("电脑读出sd内容: " + msg);
        TFCard tfCard = new TFCardImpl();
        tfCard.write("this is msg in tf ");
        SDAdapterTF sdAdapter = new SDAdapterTF(tfCard);
        msg = computer.readSDCard(sdAdapter);
        System.out.println("电脑读出tf内容: " + msg);
    }
}
