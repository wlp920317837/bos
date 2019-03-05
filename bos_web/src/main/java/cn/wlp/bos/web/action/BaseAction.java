package cn.wlp.bos.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Wlp
 * @program bos_parent
 * @description action模板类
 * @create 2019-03-04 21:35
 **/
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    public static final String HOME = "home";

    protected T t;

    @Override
    public T getModel() {
        return t;
    }

    public BaseAction() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> entityClass = (Class<T>) actualTypeArguments[0];
        try {
            T t = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}