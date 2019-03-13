package cn.wlp.bos.service;

import cn.wlp.bos.common.PageBean;
import cn.wlp.bos.domain.Subarea;

import java.util.List;

/**
 * @author Wlp
 * @date 2019-03-07 11:37
 **/
public interface SubareaService {
    /**
     * save
     *
     * @param model 分区
     * @return void
     * @author 王力鹏
     * @date 2019/3/7
     */
    void save(Subarea model);

    /**
     * pageQuery
     *
     * @param pageBean 页面信息
     * @return void
     * @author 王力鹏
     * @date 2019/3/7
     */
    void pageQuery(PageBean pageBean);

    /**
     * saveOrUpdate
     *
     * @param model 分区
     * @return void
     * @author 王力鹏
     * @date 2019/3/7
     */
    void saveOrUpdate(Subarea model);

    /**
     * deleteBatch
     *
     * @param ids 分区id
     * @return void
     * @author 王力鹏
     * @date 2019/3/7
     */
    void deleteBatch(String ids);

    /**
     * findAll
     *
     * @return java.util.List<cn.wlp.bos.domain.Subarea>
     * @author 王力鹏
     * @date 2019/3/7
     */
    List<Subarea> findAll();

    /**
     * findSubareaNoA
     *
     * @return java.util.List<cn.wlp.bos.domain.Subarea>
     * @author 王力鹏
     * @date 2019/3/7
     */
    List<Subarea> findSubareaNoA();

    /**
     * findSubareaA
     *
     * @param decidedzoneId
     * @return java.util.List<cn.wlp.bos.domain.Subarea>
     * @author 王力鹏
     * @date 2019/3/13
     */
    List<Subarea> findSubareaA(String decidedzoneId);
}
