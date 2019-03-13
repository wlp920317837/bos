package cn.wlp.bos.web.action;

import cn.wlp.bos.crm.Customer;
import cn.wlp.bos.crm.CustomerService;
import cn.wlp.bos.domain.Noticebill;
import cn.wlp.bos.service.NoticebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author Wlp
 * @date 2019-03-13 13:59
 * 通知单action
 **/
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {

    @Autowired
    private NoticebillService noticebillService;

    @Resource
    private CustomerService crmProxy;

    public String findCustomerByTel() {
        Customer customer = crmProxy.findCustomerByTel(model.getTelephone());
        java2Json(customer, null);
        return null;
    }

    public String add(){
        noticebillService.save(model);
        return "add";
    }
}
