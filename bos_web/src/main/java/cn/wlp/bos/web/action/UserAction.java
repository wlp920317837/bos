package cn.wlp.bos.web.action;

import cn.wlp.bos.domain.User;
import cn.wlp.bos.service.UserService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author Wlp
 * @program bos_parent
 * @description 用户action
 * @create 2019-03-04 21:43
 **/
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    @Autowired
    private UserService userService;

    public String getUserById() {
        User user = userService.getUserById("8a828e8763d9f53a0163d9f7c5110001");
        ServletActionContext.getRequest().setAttribute("user", user);
        return "aaa";
    }
}
