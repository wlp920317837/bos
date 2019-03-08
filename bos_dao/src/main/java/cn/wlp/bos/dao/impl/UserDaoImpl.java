package cn.wlp.bos.dao.impl;

import cn.wlp.bos.dao.UserDao;
import cn.wlp.bos.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wlp
 * @date 2019-03-05 21:52
 **/
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User findUserByUsername(String username) {
        String hql = "FROM User u WHERE u.username = ?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);
        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }
}
