package cn.wlp.bos.service.impl;

import cn.wlp.bos.common.PageBean;
import cn.wlp.bos.dao.StaffDao;
import cn.wlp.bos.domain.Staff;
import cn.wlp.bos.service.StaffService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wlp
 * @date 2019-03-05 21:55
 * staffService实现类
 **/
@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;

    @Override
    public void add(Staff model) {
        staffDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        staffDao.pageQuery(pageBean);
    }

    @Override
    public void deleteBatch(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            staffDao.executeUpdate("staff.delete", id);
        }
    }

    @Override
    public void restoreBatch(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            staffDao.executeUpdate("staff.restoreBatch", id);
        }
    }

    @Override
    public void edit(Staff model) {
        staffDao.saveOrUpdate(model);
    }

    @Override
    public List<Staff> findStaffNoA() {
        DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
        dc.add(Restrictions.ne("deltag", "1"));
        List<Staff> staffList = staffDao.findByCriteria(dc);
        return staffList;
    }
}
