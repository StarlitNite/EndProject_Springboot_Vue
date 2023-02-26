package com.endproject.Realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.endproject.entity.UserType;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/25 14:02
 */
public class UserRealm extends AuthorizingRealm {


    /**
    * @Description: 认证
    * @date 2023/2/25 14:03
    * @author WangNaiLinn
    **/

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        QueryWrapper<UserType> queryWrapper = new QueryWrapper<>();


        return null;
    }

    /**
    * @Description:授权
    * @date 2023/2/25 14:03
    * @author WangNaiLinn
    **/

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}
