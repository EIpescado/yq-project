package pers.yurwisher.clockwerk.behavioral.nullobject;

/**
 * @author yq
 * @date 2019/09/24 14:21
 * @description 空对象
 * @since V1.0.0
 */
public class NullCustomer extends AbstractCustomer {
    @Override
    public boolean isNil() {
        return true;
    }

    @Override
    public String getName() {
        return "Not Available in Customer Database";
    }
}
