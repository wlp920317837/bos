package cn.wlp.bos.service;

import cn.wlp.bos.common.PageBean;
import cn.wlp.bos.domain.Workordermanage;

/**
 * @author Wlp
 * @date 2019-03-13 15:58
 **/
public interface WorkordermanageService {
    /**
     * save
     *
     * @param model
     * @return void
     * @author 王力鹏
     * @date 2019/3/13
     */
    void save(Workordermanage model);

    /**
     * pageQuery
     *
     * @param pageBean
     * @return void
     * @author 王力鹏
     * @date 2019/3/13
     */
    void pageQuery(PageBean pageBean);
}
