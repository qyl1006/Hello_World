package cn.wolfcode.wms.realm;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.service.IRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IRoleService roleService;

    @Override
    public String getName(){
        return "cn.wolfcode.shiro.MyRealm";
    }


    /**
     * 认证
     * @param token
     * @return AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //查询数据库的账号信息
        Employee emp = employeeService.selectByUsername((String) token.getPrincipal());

        //判断令牌的用户名是否在数据库中 如果在就返回认证信息对象  如果不在直接返回null
        if(emp != null){

            //传入主身份信息, 正确的凭证, realm的名字
            return new SimpleAuthenticationInfo(emp,emp.getPassword(), getName());
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
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Employee emp = (Employee) token.getPrimaryPrincipal();

        if (!emp.isAdmin()){
            info.addRoles(Arrays.asList("ADMIN"));
            info.addStringPermission("*:*");
        }else{
            //添加角色
            //查数据库
            List<String> roles = roleService.selectSnByEmployeeId(emp.getId());

            info.addRoles(roles);
            //添加权限
            List<String> permissionList = permissionService.selectExpressionByEmployeeId(emp.getId());
            info.addStringPermissions(permissionList);

        }

        return info;

    }




}
