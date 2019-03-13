package cn.wlp.bos.service.impl;

import cn.wlp.bos.common.BosUtils;
import cn.wlp.bos.crm.CustomerService;
import cn.wlp.bos.dao.DecidedzoneDao;
import cn.wlp.bos.dao.NoticebillDao;
import cn.wlp.bos.dao.WorkbillDao;
import cn.wlp.bos.domain.*;
import cn.wlp.bos.service.NoticebillService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * @author Wlp
 * @date 2019-03-13 14:00
 * 通知单service实现类
 **/
@Service
@Transactional
public class NoticebillServiceImpl implements NoticebillService {

    @Autowired
    private NoticebillDao noticebillDao;

    @Resource
    private CustomerService crmProxy;

    @Autowired
    private WorkbillDao workbillDao;

    @Autowired
    private DecidedzoneDao decidedzoneDao;

    @Override
    public void save(Noticebill model) {
        //补全noticebill信息。尝试自动分单
        User user = BosUtils.getUser();
        model.setUser(user);
        //保存通知单
        noticebillDao.save(model);

        String decidedzoneId = crmProxy.findDecidedzoneIdByAddress(model.getPickaddress());
        if (StringUtils.isNotBlank(decidedzoneId)) {
            //自动分单
            model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
            //获得定区id
            Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
            Staff staff = decidedzone.getStaff();
            model.setStaff(staff);
            //为取派员生成工单
            Workbill workbill = new Workbill();
            workbill.setAttachbilltimes(0);
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
            workbill.setNoticebill(model);
            workbill.setPickstate(Workbill.PICKSTATE_NO);
            workbill.setRemark(model.getRemark());
            workbill.setStaff(staff);
            workbill.setStaff(staff);
            workbill.setType(Workbill.TYPE_1);
            //保存工单
            workbillDao.save(workbill);
        } else {
            //手动分单
            model.setOrdertype(Noticebill.ORDERTYPE_MAN);
        }
    }
}
