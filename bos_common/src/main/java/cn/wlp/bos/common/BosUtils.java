package cn.wlp.bos.common;

import cn.wlp.bos.domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

/**
 * @author Wlp
 * @date 2019-03-05 18:38
 * 获得session工具类
 **/
public class BosUtils {
    public static HttpSession getSession() {
        return ServletActionContext.getRequest().getSession();
    }

    public static User getUser() {
        return (User) getSession().getAttribute("loginUser");
    }
}
