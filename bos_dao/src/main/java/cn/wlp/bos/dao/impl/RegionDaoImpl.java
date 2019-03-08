package cn.wlp.bos.dao.impl;

import cn.wlp.bos.dao.RegionDao;
import cn.wlp.bos.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wlp
 * @date 2019-03-06 19:50
 * 区域dao
 **/
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao {

    @Override
    public List<Region> findByQ(String q) {
        String hql = "from Region r where r.province like ? or r.city like ?" +
                "or r.district like ? or r.citycode like ? or r.shortcode like ?";
        List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql, "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%");
        return list;
    }
}
