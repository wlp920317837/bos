package cn.wlp.bos.service.impl;

import cn.wlp.bos.dao.UserDao;
import cn.wlp.bos.domain.User;
import cn.wlp.bos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.io.Serializable;

/**
 * @author Wlp
 * @date 2019-03-05 21:52
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Serializable id) {
        return userDao.findById(id);
    }

    @Override
    public User login(User model) {
        User user = userDao.findUserByUsername(model.getUsername());
        if (user != null) {
            String pwd = DigestUtils.md5DigestAsHex(model.getPassword().getBytes());
            String password = user.getPassword();
            if (password.equals(pwd)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void editPassword(User user) {
        String psw = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(psw);
        userDao.update(user);
    }
}
