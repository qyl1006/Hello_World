package cn.wolfcode.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;

public class MyRealm extends AuthorizingRealm {
    @Override
    public String getName(){
        return "cn.wolfcode.shiro.MyRealm";
    }


    /**
     * 认证
     * @param token
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //准备数据库的账号信息
        String username = "zhangsan";
        String password = "2f1f526e25fdefa341c7a302b47dd9df";

        //判断令牌的用户名是否在数据库中 如果在就返回认证信息对象  如果不在直接返回null
        if(username.equals(token.getPrincipal())){

            //传入主身份信息, 正确的凭证, realm的名字
            return new SimpleAuthenticationInfo(username,password, ByteSource.Util.bytes(username),getName());
        }
        return null;
    }


    /**
     * 授权
     * @param token
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        //获取当前登陆用户的ID
        //通过用户ID 去数据库查询出当前用户拥有的角色和权限的集合

        //模拟
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加角色
        info.addRoles(Arrays.asList("HR", "MANAGER"));

        //添加权限
        info.addStringPermissions(Arrays.asList("user:create", "user:delete"));

        return info;

    }
}
