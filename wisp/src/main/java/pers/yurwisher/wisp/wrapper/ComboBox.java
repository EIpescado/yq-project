package pers.yurwisher.wisp.wrapper;

import java.io.Serializable;

/**
 * Created by yq on 2017/06/21 17:13.
 * 下拉框数据包装类
 */
public class ComboBox<ID,CODE,NAME> implements Serializable {
    private static final long serialVersionUID = -5298789943734343220L;

    private ID id;
    private CODE code;
    private NAME name;

    public ComboBox() {

    }

    public ComboBox(ID id, CODE code, NAME name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public ID getId() {
        return id;
    }

    public CODE getCode() {
        return code;
    }

    public NAME getName() {
        return name;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void setCode(CODE code) {
        this.code = code;
    }

    public void setName(NAME name) {
        this.name = name;
    }
}
