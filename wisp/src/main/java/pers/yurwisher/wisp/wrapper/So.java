package pers.yurwisher.wisp.wrapper;

/**
 * @author yq
 * @date 2019/12/17 10:35
 * @description select object,下拉框对象
 * @since V1.0.0
 */
public class So<T> {

    private String label;
    private T value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public So() {
    }

    public So(String label, T value) {
        this.label = label;
        this.value = value;
    }
}
