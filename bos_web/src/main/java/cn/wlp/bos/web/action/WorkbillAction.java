package cn.wlp.bos.web.action;

import cn.wlp.bos.domain.Staff;
import cn.wlp.bos.domain.Workbill;
import cn.wlp.bos.service.WorkbillService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author Wlp
 * @date 2019-03-13 14:56
 * 工单action
 **/
@Controller
@Scope("prototype")
public class WorkbillAction extends BaseAction<Workbill> {

    @Autowired
    private WorkbillService workbillService;

    public String pageQuery() {
        DetachedCriteria dc = pageBean.getDetachedCriteria();
        Staff staff = model.getStaff();
        if (staff != null) {
            String name = staff.getName();
            String telephone = staff.getTelephone();
            dc.createAlias("staff", "s");
            if (StringUtils.isNotBlank(name)) {
                dc.add(Restrictions.like("s.name", "%" + name + "%"));
            }
            if (StringUtils.isNotBlank(telephone)) {
                dc.add(Restrictions.like("s.telephone", "%" + telephone + "%"));
            }
        }
        workbillService.pageQuery(pageBean);
        java2Json(pageBean, new String[]{"decidedzones", "user", "workbills"});
        return null;
    }

    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String repeat() {
        workbillService.repeat(ids);
        return LIST;
    }

    public String cancel() {
        workbillService.cancel(ids);
        return LIST;
    }
}
