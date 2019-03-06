package cn.wlp.bos.service;

import cn.wlp.bos.common.PageBean;
import cn.wlp.bos.domain.Staff;

/**
 * @author Wlp
 * @date 2019-03-05 21:55
 **/
public interface StaffService {
    /**
     * add
     *
     * @param model staff实体
     * @return void
     * @author 王力鹏
     * @date 2019/3/6
     */
    void add(Staff model);

    /**
     * pageQuery
     *
     * @param pageBean 页面对象
     * @return void
     * @author 王力鹏
     * @date 2019/3/6
     */
    void pageQuery(PageBean pageBean);

    /**
     * deleteBatch
     *
     * @param ids 需要删除的员工id
     * @return void
     * @author 王力鹏
     * @date 2019/3/6
     */
    void deleteBatch(String ids);

    /**
     * redelBatch
     *
     * @param ids 要恢复的员工id
     * @return void
     * @author 王力鹏
     * @date 2019/3/6
     */
    void restoreBatch(String ids);

    /**
     * edit
     *
     * @param model 取派员实体
     * @return void
     * @author 王力鹏
     * @date 2019/3/6
     */
    void edit(Staff model);
}
