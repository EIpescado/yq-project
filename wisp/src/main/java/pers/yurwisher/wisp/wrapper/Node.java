package pers.yurwisher.wisp.wrapper;


import java.util.List;

/**
 * @author yq
 * @date 2019/07/23 11:20
 * @description 节点
 * @since V1.0.0
 */
public class Node<ID,T extends Node> {

    private ID id;

    private ID pid;

    private List<T> children;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getPid() {
        return pid;
    }

    public void setPid(ID pid) {
        this.pid = pid;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
