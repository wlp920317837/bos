package cn.wlp.bos.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author Wlp
 * @program bos_parent
 * @description dao模板
 * @create 2019-03-04 21:45
 **/
public interface BaseDao<T> {
    public void save(T entity);

    public void delete(T entity);

    public void update(T entity);

    public T findById(Serializable id);

    public List<T> findAll();
}
