package cn.wlp.bos.web.action;

import cn.wlp.bos.domain.Workordermanage;
import cn.wlp.bos.service.WorkordermanageService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * @author Wlp
 * @date 2019-03-13 15:57
 **/
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {

    @Autowired
    private WorkordermanageService workordermanageService;

    public String add() throws IOException {
        String f = "1";
        try {
            workordermanageService.save(model);
        } catch (Exception e) {
            e.printStackTrace();
            f = "0";
        }
        ServletActionContext.getResponse().setContentType("text/html;utf-8");
        ServletActionContext.getResponse().getWriter().println(f);
        return null;
    }

    ;

    public String pageQuery() {
        workordermanageService.pageQuery(pageBean);
        java2Json(pageBean, null);
        return null;
    }
}
