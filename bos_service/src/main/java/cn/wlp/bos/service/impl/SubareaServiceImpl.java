package cn.wlp.bos.service.impl;

import cn.wlp.bos.common.PageBean;
import cn.wlp.bos.dao.SubareaDao;
import cn.wlp.bos.domain.Subarea;
import cn.wlp.bos.service.SubareaService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wlp
 * @date 2019-03-07 11:38
 * 分区service实现类
 **/
@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {
    @Autowired
    private SubareaDao subareaDao;

    @Override
    public void save(Subarea model) {
        subareaDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    @Override
    public void saveOrUpdate(Subarea model) {
        subareaDao.saveOrUpdate(model);
    }

    @Override
    public void deleteBatch(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            subareaDao.deleteById(id);
        }
    }

    @Override
    public List<Subarea> findAll() {
        List<Subarea> list = subareaDao.findAll();
        return list;
    }

    @Override
    public List<Subarea> findSubareaNoA() {
        DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
        dc.add(Restrictions.isNull("decidedzone"));
        List<Subarea> list = subareaDao.findByCriteria(dc);
        return list;
    }

    @Override
    public List<Subarea> findSubareaA(String decidedzoneId) {
        DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
        dc.add(Restrictions.eq("decidedzone.id", decidedzoneId));
        List<Subarea> list = subareaDao.findByCriteria(dc);
        return list;
    }
}
