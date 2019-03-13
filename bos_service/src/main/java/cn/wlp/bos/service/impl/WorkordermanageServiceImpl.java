package cn.wlp.bos.service.impl;

import cn.wlp.bos.common.PageBean;
import cn.wlp.bos.dao.WorkordermanageDao;
import cn.wlp.bos.domain.Workordermanage;
import cn.wlp.bos.service.WorkordermanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wlp
 * @date 2019-03-13 15:58
 **/
@Service
@Transactional
public class WorkordermanageServiceImpl implements WorkordermanageService {

    @Autowired
    private WorkordermanageDao workordermanageDao;

    @Override
    public void save(Workordermanage model) {
        workordermanageDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        workordermanageDao.pageQuery(pageBean);
    }
}
