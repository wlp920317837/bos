package cn.wlp.bos.dao.impl;

import cn.wlp.bos.common.PageBean;
import cn.wlp.bos.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Wlp
 * @program bos_parent
 * @description dao模板实现类
 * @create 2019-03-04 21:47
 **/
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    private Class<T> entityClass;

    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public BaseDaoImpl() {
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        entityClass = (Class<T>) actualTypeArguments[0];
    }

    @Override
    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    @Override
    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    @Override
    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        String hql = "FROM " + entityClass.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        Integer page = pageBean.getPage();
        Integer pageSize = pageBean.getPageSize();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        //查询总条数
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list1 = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list1.size() != 0) {
            pageBean.setTotal(list1.get(0));
        }
        //查询每页信息
        detachedCriteria.setProjection(null);
        Integer start = (page - 1) * pageSize;
        Integer end = pageSize;
        List list = this.getHibernateTemplate().findByCriteria(detachedCriteria, start, end);
        if (list.size() != 0) {
            pageBean.setRows(list);
        }
    }

    @Override
    public void executeUpdate(String queryName, Object... objects) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.getNamedQuery(queryName);
        int i = 0;
        for (Object obj : objects) {
            query.setParameter(i, obj);
        }
        query.executeUpdate();
    }

    @Override
    public void saveOrUpdate(T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }
}
