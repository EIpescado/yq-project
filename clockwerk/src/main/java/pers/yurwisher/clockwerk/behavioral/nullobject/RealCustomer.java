package pers.yurwisher.clockwerk.behavioral.nullobject;

/**
 * @author yq
 * @date 2019/09/24 14:20
 * @description 有效对象
 * @since V1.0.0
 */
public class RealCustomer extends AbstractCustomer {

    private String name;

    public RealCustomer(String name) {
        this.name = name;
    }

    @Override
    public boolean isNil() {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
