package pers.yurwisher.wisp.wrapper;

import java.io.Serializable;

/**
 * 下拉选择包装
 *  @author  Created by yq on 2017/06/21 16:59.
 */
public class KeyValue<K,V> implements Serializable {
    private static final long serialVersionUID = -2304293451907099054L;
    private K key;
    private V value;

    public KeyValue() {

    }

    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{"+ key +":"+ value + "}";
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyValue<?, ?> keyValue = (KeyValue<?, ?>) o;

        if (getKey() != null ? !getKey().equals(keyValue.getKey()) : keyValue.getKey() != null) return false;
        return getValue() != null ? getValue().equals(keyValue.getValue()) : keyValue.getValue() == null;

    }

    @Override
    public int hashCode() {
        int result = getKey() != null ? getKey().hashCode() : 0;
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        return result;
    }
}
