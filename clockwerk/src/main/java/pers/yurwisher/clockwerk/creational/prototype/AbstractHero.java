package pers.yurwisher.clockwerk.creational.prototype;

/**
 * @author yq
 * @date 2019/09/19 11:01
 * @description 英雄
 * @since V1.0.0
 */
public abstract class AbstractHero implements Cloneable {

    private String id;
    protected String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public abstract void skill();

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
