package cn.wlp.bos.dao;

import cn.wlp.bos.domain.User;

/**
 * @author Wlp
 * @date 2019-03-05 21:52
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
