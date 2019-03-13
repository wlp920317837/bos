package cn.wlp.bos.service.impl;

import cn.wlp.bos.common.PageBean;
import cn.wlp.bos.dao.WorkbillDao;
import cn.wlp.bos.service.WorkbillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wlp
 * @date 2019-03-13 14:57
 * workbillService实现类
 **/
@Service
@Transactional
public class WorkbillServiceImpl implements WorkbillService {

    @Autowired
    private WorkbillDao workbillDao;

    @Override
    public void pageQuery(PageBean pageBean) {
        workbillDao.pageQuery(pageBean);
    }

    @Override
    public void repeat(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            workbillDao.executeUpdate("workbill.repeat", id);
        }
    }

    @Override
    public void cancel(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            workbillDao.executeUpdate("workbill.cancel", id);
        }
    }
}
