package cn.wlp.bos.service;

import cn.wlp.bos.domain.User;

import java.io.Serializable;

/**
 * @author Wlp
 * @date 2019-03-05 21:52
 **/
public interface UserService {

    /**
     * getUserById
     *
     * @param i id
     * @return cn.wlp.bos.domain.User
     * @author 王力鹏
     * @date 2019/3/5
     */
    User getUserById(Serializable i);

    /**
     * login
     *
     * @param model 用户实体
     * @return cn.wlp.bos.domain.User
     * @author 王力鹏
     * @date 2019/3/5
     */
    User login(User model);

    /**
     * editPassword
     *
     * @param user 用户
     * @return void
     * @author 王力鹏
     * @date 2019/3/5
     */
    void editPassword(User user);
}
