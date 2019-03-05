package cn.wlp.bos.service;

import cn.wlp.bos.domain.User;

import java.io.Serializable;

/**
 * @author Wlp
 * @program bos_parent
 * @description
 * @create 2019-03-04 22:03
 **/
public interface UserService {

    User getUserById(Serializable i);
}
