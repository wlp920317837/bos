package cn.wlp.bos.dao;

import cn.wlp.bos.domain.Region;

import java.util.List;

/**
 * @author Wlp
 * @date 2019-03-06 19:48
 **/
public interface RegionDao extends BaseDao<Region> {

    /**
     * findByQ
     *
     * @param q 条件
     * @return cn.wlp.bos.domain.Region
     * @author 王力鹏
     * @date 2019/3/7
     */
    List<Region> findByQ(String q);
}