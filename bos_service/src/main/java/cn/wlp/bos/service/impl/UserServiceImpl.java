package cn.wlp.bos.service.impl;

import cn.wlp.bos.dao.UserDao;
import cn.wlp.bos.domain.User;
import cn.wlp.bos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author Wlp
 * @program bos_parent
 * @description userService实现类
 * @create 2019-03-04 22:04
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
}
