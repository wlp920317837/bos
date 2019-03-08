package cn.wlp.bos.service;

import cn.wlp.bos.common.PageBean;
import cn.wlp.bos.domain.Decidedzone;

/**
 * @author Wlp
 * @date 2019-03-08 10:50
 **/
public interface DecidedzoneService {
    /**
     * add
     *
     * @param model
     * @param subareaid
     * @return void
     * @author 王力鹏
     * @date 2019/3/8
     */
    void add(Decidedzone model, String[] subareaid);

    /**
     * pageQuery
     *
     * @param pageBean
     * @return void
     * @author 王力鹏
     * @date 2019/3/8
     */
    void pageQuery(PageBean pageBean);
}
