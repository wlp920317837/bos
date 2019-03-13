package cn.wlp.bos.service;

import cn.wlp.bos.common.PageBean;

/**
 * @author Wlp
 * @date 2019-03-13 14:57
 **/
public interface WorkbillService {
    /**
     * pageQuery
     *
     * @param pageBean
     * @return void
     * @author 王力鹏
     * @date 2019/3/13
     */
    void pageQuery(PageBean pageBean);

    /**
     * repeat
     *
     * @param ids
     * @return void
     * @author 王力鹏
     * @date 2019/3/13
     */
    void repeat(String ids);

    /**
     * cancel
     *
     * @param ids
     * @return void
     * @author 王力鹏
     * @date 2019/3/13
     */
    void cancel(String ids);
}
