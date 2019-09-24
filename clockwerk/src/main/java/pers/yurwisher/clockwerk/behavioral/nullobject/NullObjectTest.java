package pers.yurwisher.clockwerk.behavioral.nullobject;

/**
 * @author yq
 * @date 2019/09/24 14:23
 * @description 空对象模式测试
 * @since V1.0.0
 */
public class NullObjectTest {

    public static void main(String[] args) {

        AbstractCustomer customer1 = CustomerFactory.getCustomer("Rob");
        AbstractCustomer customer2 = CustomerFactory.getCustomer("Bob");
        AbstractCustomer customer3 = CustomerFactory.getCustomer("Julie");
        AbstractCustomer customer4 = CustomerFactory.getCustomer("Laura");

        System.out.println(customer1.getName());
        System.out.println(customer2.getName());
        System.out.println(customer3.getName());
        System.out.println(customer4.getName());
    }
}


