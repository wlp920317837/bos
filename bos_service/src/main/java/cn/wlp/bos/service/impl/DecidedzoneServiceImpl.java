package cn.wlp.bos.service.impl;

import cn.wlp.bos.common.PageBean;
import cn.wlp.bos.dao.DecidedzoneDao;
import cn.wlp.bos.domain.Decidedzone;
import cn.wlp.bos.domain.Subarea;
import cn.wlp.bos.service.DecidedzoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Wlp
 * @date 2019-03-08 10:50
 * 定区service实现类
 **/
@Service
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService {

    @Autowired
    private DecidedzoneDao decidedzoneDao;

    @Override
    public void add(Decidedzone model, String[] subareaid) {
        Set<Subarea> subareaSet = new HashSet<>();
        for (String id : subareaid) {
            Subarea subarea = new Subarea(id);
            subareaSet.add(subarea);
        }
        model.setSubareas(subareaSet);
        decidedzoneDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        decidedzoneDao.pageQuery(pageBean);
    }
}
