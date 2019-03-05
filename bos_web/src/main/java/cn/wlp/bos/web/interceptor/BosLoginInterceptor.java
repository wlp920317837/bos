package cn.wlp.bos.web.interceptor;

import cn.wlp.bos.common.BosUtils;
import cn.wlp.bos.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * @author Wlp
 * @date 2019-03-05 19:20
 * 登录拦截器
 **/
public class BosLoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        User loginUser = (User) BosUtils.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            return "login";
        }
        return invocation.invoke();
    }
}
