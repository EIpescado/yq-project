package pers.yurwisher.wisp.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

/**
 * 集合工具类
 *
 * @author yq on 2017/08/16 14:41.
 */
public class CollectionUtils {

    private CollectionUtils() {

    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map map) {
        return map != null && !map.isEmpty();
    }

    /**
     * 返回集合中第一个equals的值
     *
     * @param collection 集合
     * @param e          过滤的元素
     * @param <E>        类型
     */
    public static <E> E findFirstEquals(Collection<E> collection, E e) {
        if (isNotEmpty(collection)) {
            Optional<E> optional = collection.stream().filter(obj ->
                    obj.equals(e)
            ).findFirst();
            return optional.isPresent() ? optional.get() : null;
        }
        return null;
    }

    public static Integer size(Collection collection) {
        return collection != null ? collection.size() : 0;
    }

    public static Integer size(Map map) {
        return map != null ? map.size() : 0;
    }

    public static boolean containsAny(Collection<?> coll1, Collection<?> coll2) {
        if (!isEmpty(coll1) && !isEmpty(coll2)) {
            Iterator it;
            Object object;
            if (coll1.size() < coll2.size()) {
                it = coll1.iterator();

                while (it.hasNext()) {
                    object = it.next();
                    if (coll2.contains(object)) {
                        return true;
                    }
                }
            } else {
                it = coll2.iterator();

                while (it.hasNext()) {
                    object = it.next();
                    if (coll1.contains(object)) {
                        return true;
                    }
                }
            }

            return false;
        } else {
            return false;
        }
    }
}
