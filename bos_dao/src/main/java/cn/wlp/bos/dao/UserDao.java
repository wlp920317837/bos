package cn.wlp.bos.dao;

import cn.wlp.bos.domain.User;

/**
 * @author Wlp
 * @program bos_parent
 * @description
 * @create 2019-03-04 22:07
 **/
public interface UserDao extends BaseDao<User> {

    /**
     * findUserByUsernameAndPassword
     *
     * @param username 用户名
     * @return cn.wlp.bos.domain.User
     * @author 王力鹏
     * @date 2019/3/5
     */
    User findUserByUsername(String username);
}
