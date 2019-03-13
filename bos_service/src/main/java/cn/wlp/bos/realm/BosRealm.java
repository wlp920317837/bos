package cn.wlp.bos.realm;

import cn.wlp.bos.dao.UserDao;
import cn.wlp.bos.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Wlp
 * @date 2019-03-13 20:46
 **/
public class BosRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            return null;
        }
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

}
