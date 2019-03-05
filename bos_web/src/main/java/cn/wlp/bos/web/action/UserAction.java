package cn.wlp.bos.web.action;

import cn.wlp.bos.common.BosUtils;
import cn.wlp.bos.domain.User;
import cn.wlp.bos.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * @author Wlp
 * @program bos_parent
 * @description 用户action
 * @create 2019-03-04 21:43
 **/
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    @Autowired
    private UserService userService;

    public String login() {
        String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        if (StringUtils.isNotBlank(checkcode) && checkcode.equals(key)) {
            //输入验证码正确
            User user = userService.login(model);
            if (user == null) {
                this.addActionError("输入的用户名或密码有误！");
                return LOGIN;
            }
            BosUtils.getSession().setAttribute("loginUser", user);
            return HOME;
        } else {
            //输入提示信息
            this.addActionError("输入验证码有误！");
            return LOGIN;
        }
    }

    public String logout() {
        BosUtils.getSession().setAttribute("loginUser", null);
        return LOGIN;
    }

    public String editPassword() throws IOException {
        String f = "1";
        User user = BosUtils.getUser();
        String password = model.getPassword();
        user.setPassword(password);
        try {
            userService.editPassword(user);
        } catch (Exception e) {
            f = "0";
            e.printStackTrace();
        }
        ServletActionContext.getResponse().getWriter().write(f);
        return null;
    }
}
