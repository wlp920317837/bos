package cn.wlp.bos.dao;

import cn.wlp.bos.common.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * @author Wlp
 * @date 2019-03-05 21:52
 **/
public interface BaseDao<T> {
    public void save(T entity);

    public void delete(T entity);

    public void update(T entity);

    public T findById(Serializable id);

    public List<T> findAll();

    void pageQuery(PageBean pageBean);

    void executeUpdate(String queryName, Object... objects);

    void saveOrUpdate(T entity);

    void deleteById(String id);

    List findByCriteria(DetachedCriteria dc);
}
