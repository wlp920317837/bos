package cn.wlp.bos.web.action;

import cn.wlp.bos.crm.Customer;
import cn.wlp.bos.crm.CustomerService;
import cn.wlp.bos.domain.Decidedzone;
import cn.wlp.bos.domain.Staff;
import cn.wlp.bos.domain.Subarea;
import cn.wlp.bos.service.DecidedzoneService;
import cn.wlp.bos.service.SubareaService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wlp
 * @date 2019-03-08 10:48
 **/
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

    @Autowired
    private DecidedzoneService decidedzoneService;

    @Autowired
    private SubareaService subareaService;

    @Resource
    private CustomerService crmProxy;

    private String[] subareaid;

    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }

    public String add() {
        decidedzoneService.add(model, subareaid);
        return LIST;
    }

    public String pageQuery() {
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        if (model.getId() != null) {
            detachedCriteria.add(Restrictions.like("id", "%" + model.getId() + "%"));
        }
        Staff staff = model.getStaff();
        if (staff != null) {
            String station = staff.getStation();
            detachedCriteria.createAlias("staff", "s");
            if (StringUtils.isNotBlank(station)) {
                detachedCriteria.add(Restrictions.like("s.station", "%" + station + "%"));
            }
        }
        decidedzoneService.pageQuery(pageBean);
        java2Json(pageBean, new String[]{"subareas", "decidedzones"});
        return null;
    }

    public String findListNotAssociation() {
        List<Customer> list = crmProxy.findListNotAssociation();
        java2Json(list, null);
        return null;
    }

    public String findListHasAssociation() {
        String id = model.getId();
        List<Customer> list = crmProxy.findListHasAssociation(id);
        java2Json(list, null);
        return null;
    }

    private List<Integer> customerIds;

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }

    public String assigncustomerstodecidedzone() {
        crmProxy.assigncustomerstodecidedzone(model.getId(), customerIds);
        return LIST;
    }

    public String listAjaxAssociation() {
        String decidedzoneId = model.getId();
        List<Subarea> subareaList = subareaService.findSubareaA(decidedzoneId);
        java2Json(subareaList, new String[]{"decidedzone", "subareas"});
        return null;
    }
}
