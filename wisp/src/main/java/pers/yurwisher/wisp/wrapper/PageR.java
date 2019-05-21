package pers.yurwisher.wisp.wrapper;

import java.io.Serializable;
import java.util.List;

/**
 * 分页包装类 根据前端需要变更
 * @author yq
 */
public class PageR<T> implements Serializable {

    private static final long serialVersionUID = -8041229965272347163L;

    /**
     * 总条数
     */
    private long total;

    /**
     * 总页数
     */
    private long pages;

    /**
     * 数据
     */
    private List<T> rows;

    public PageR() {
        this.rows = null;
    }

    public long getTotal() {
        return total;
    }

    public long getPages() {
        return pages;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
