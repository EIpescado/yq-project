package pers.yurwisher.clockwerk.behavioral.iterator;

/**
 * @author yq
 * @date 2019/09/23 17:41
 * @description 迭代器
 * @since V1.0.0
 */
public interface Iterator {
    /**
     * 是否存在下一个元素
     */
    boolean hasNext();

    /**
     * 下一个元素
     */
    Object next();
}
