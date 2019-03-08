package cn.wlp.bos.web.action;

import cn.wlp.bos.domain.Staff;
import cn.wlp.bos.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author Wlp
 * @date 2019-03-05 21:52
 * 员工action
 **/
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

    @Autowired
    private StaffService staffService;

    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String add() {
        staffService.add(model);
        return LIST;
    }

    public String pageQuery() {
        String name = model.getName();
        String telephone = model.getTelephone();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        if (StringUtils.isNotBlank(name)) {
            detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
        }
        if (StringUtils.isNotBlank(telephone)) {
            detachedCriteria.add(Restrictions.like("telephone", "%" + telephone + "%"));
        }
        staffService.pageQuery(pageBean);
        java2Json(pageBean, new String[]{"page", "pageSize", "decidedzones"});
        return null;
    }

    public String deleteBatch() {
        staffService.deleteBatch(ids);
        return LIST;
    }

    public String restoreBatch() {
        staffService.restoreBatch(ids);
        return LIST;
    }

    public String edit() {
        staffService.edit(model);
        return LIST;
    }

    public String findStaffNoA() {
        List<Staff> staffList = staffService.findStaffNoA();
        java2Json(staffList, new String[]{"decidedzones"});
        return null;
    }
}