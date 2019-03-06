package cn.wlp.bos.web.action;

import cn.wlp.bos.common.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Wlp
 * @program bos_parent
 * @description action模板类
 * @create 2019-03-04 21:35
 **/
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    public static final String HOME = "home";
    public static final String LIST = "list";

    protected PageBean pageBean = new PageBean();
    DetachedCriteria detachedCriteria = null;

    public void setPage(int page) {
        pageBean.setPage(page);
    }

    public void setRows(int rows) {
        pageBean.setPageSize(rows);
    }

    protected T model;

    @Override
    public T getModel() {
        return model;
    }

    public BaseAction() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> entityClass = (Class<T>) actualTypeArguments[0];
        detachedCriteria = DetachedCriteria.forClass(entityClass);
        pageBean.setDetachedCriteria(detachedCriteria);
        try {
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void java2Json(Object o, String[] exclueds) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(exclueds);
        String json = JSONObject.fromObject(o, jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void java2Json(List o, String[] exclueds) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(exclueds);
        String json = JSONArray.fromObject(o, jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
