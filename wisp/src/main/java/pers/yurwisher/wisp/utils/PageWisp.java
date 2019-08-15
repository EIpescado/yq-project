package pers.yurwisher.wisp.utils;

import pers.yurwisher.wisp.wrapper.PageR;

import java.util.List;

/**
 * @author yq
 * @date 2019/08/15 10:39
 * @description 分页工具
 * @since V1.0.0
 */
public class PageWisp<T> {

    /**
     * 分页
     * @param total 总数量
     * @param size page size
     * @param page 页数 从1开始
     * @param holder 数据持有着
     * @return 分页结果
     */
    public PageR<T> get(Long total,Long size,Long page,DataHolder<T> holder){
        Asserts.notNull(size);
        PageR<T> result = new PageR<>();
        //总数量
        total = total != null ? total : 0L;
        if(total == 0L){
            return result;
        }
        page = page != null ? page : 1L;
        result.setTotal(total);
        //总页数
        long pages = total / size;
        if (total % size != 0) {
            pages++;
        }
        result.setPages(pages);
        //查询的页码超过总页数
        if (page > pages) {
            return result;
        }
        Long from = (page - 1) * size;
        Long end = page * size;
        //最后index 大于总数量
        end = end > total ? total : end;
        result.setRows(holder.get(from,end));
        return result;
    }

    public interface DataHolder<T>{
        /**
         * 获取数据
         * @param from 起始
         * @param end 结束
         * @return 数据集合
         */
        List<T> get(Long from,Long end);
    }
}
