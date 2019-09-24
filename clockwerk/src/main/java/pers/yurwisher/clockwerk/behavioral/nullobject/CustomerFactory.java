package pers.yurwisher.clockwerk.behavioral.nullobject;

/**
 * @author yq
 * @date 2019/09/24 14:22
 * @description 工厂
 * @since V1.0.0
 */
public class CustomerFactory {

    public static final String[] NAMES = {"Rob", "Joe", "Julie"};

    public static AbstractCustomer getCustomer(String name){
        for (int i = 0; i < NAMES.length; i++) {
            if (NAMES[i].equalsIgnoreCase(name)){
                return new RealCustomer(name);
            }
        }
        return new NullCustomer();
    }
}
