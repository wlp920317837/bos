package cn.wlp.bos.common;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @author Wlp
 * @date 2019-03-06 14:29
 * 分页查询页面类
 **/
public class PageBean {
    private int page;
    private int pageSize;
    private List rows;
    private Long total;
    private DetachedCriteria detachedCriteria;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }
}
