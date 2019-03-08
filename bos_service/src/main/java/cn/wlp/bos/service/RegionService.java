package cn.wlp.bos.service;

import cn.wlp.bos.common.PageBean;
import cn.wlp.bos.domain.Region;

import java.util.List;

/**
 * @author Wlp
 * @date 2019-03-06 19:46
 **/
public interface RegionService {

    /**
     * saveBatch
     * 批量保存区域
     *
     * @param regionList 区域list
     * @return void
     * @author 王力鹏
     * @date 2019/3/6
     */
    void saveBatch(List<Region> regionList);

    /**
     * pageQuery
     *
     * @param pageBean 每页对象
     * @return void
     * @author 王力鹏
     * @date 2019/3/6
     */
    void pageQuery(PageBean pageBean);

    /**
     * add
     *
     * @param model 区域
     * @return void
     * @author 王力鹏
     * @date 2019/3/6
     */
    void add(Region model);

    /**
     * edit
     *
     * @param model 区域
     * @return void
     * @author 王力鹏
     * @date 2019/3/6
     */
    void edit(Region model);

    /**
     * deleteBatch
     *
     * @param ids 区域id
     * @return void
     * @author 王力鹏
     * @date 2019/3/7
     */
    void deleteBatch(String ids);

    /**
     * findByQ
     *
     * @param q 条件
     * @return cn.wlp.bos.domain.Region
     * @author 王力鹏
     * @date 2019/3/7
     */
    List<Region> findByQ(String q);

    /**
     * findAll
     *
     * @return java.util.List<cn.wlp.bos.domain.Region> 区域集合
     * @author 王力鹏
     * @date 2019/3/7
     */
    List<Region> findAll();

}