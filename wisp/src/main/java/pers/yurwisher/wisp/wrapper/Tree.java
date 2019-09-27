package pers.yurwisher.wisp.wrapper;

import pers.yurwisher.wisp.utils.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yq
 * @date 2019/07/23 11:33
 * @description 树
 * @since V1.0.0
 */
public class Tree<ID,T extends Node<ID,T>> {

    private ID rootId;

    public Tree(ID rootId) {
        this.rootId = rootId;
    }

    public List<T> build(List<T> list){
        if(CollectionUtils.isNotEmpty(list)){
            //按父菜单分组
            Map<ID,List<T>> map = list.stream().peek(n ->{
                if(n.getPid() == null){
                    n.setPid(rootId);
                }
            }).collect(Collectors.groupingBy(Node::getPid));
            //顶级菜单
            List<T> baseMenus = map.get(rootId);
            return loop(baseMenus,map);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private  List<T> loop(List<T> list,Map<ID,List<T>> map){
        for (T node : list){
            node.setChildren(map.get(node.getId()));
            if(CollectionUtils.isNotEmpty(node.getChildren())){
                loop(node.getChildren(),map);
            }
        }
        return list;
    }

}
