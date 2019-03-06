package cn.wlp.bos.web.action;

import cn.wlp.bos.domain.Staff;
import cn.wlp.bos.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
}