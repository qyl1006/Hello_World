import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

public class App {
    @Test
    public void testLogin(){
        //构造安全管理器的环境
        //1 创建安全管理器的工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shrio-permission.ini");

        //2. 通过工厂获取安全管理器的实例
        SecurityManager securityManager = factory.getInstance();

        //3. 把安全管理器设置到当前的环境中
        SecurityUtils.setSecurityManager(securityManager);

        //执行认证
        //3.1 获取主体对象
        Subject subject = SecurityUtils.getSubject();

        //3.2 执行认证操作(带上令牌)
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            System.out.println("用户名不存在");
            e.printStackTrace();
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
            e.printStackTrace();
        }

        //使用ini的方式来配置用户 角色 权限  进行权限/角色判断测试
        // 判断当前用户是否拥有某个角色
        System.out.println(subject.hasRole("role1"));

        //判断当前用户是否拥有列出的所有角色
        System.out.println(subject.hasAllRoles(Arrays.asList("role1", "role3")));

        //返回所有角色判断的数组结果
        System.out.println(Arrays.toString(subject.hasRoles(Arrays.asList("role1", "role3"))));

        //check的方式 如果没有权限会抛异常
//        subject.checkRole("role3");  //UnauthorizedException: Subject does not have role [role3]

        System.out.println("--------------------------");
        //判断当前用户是否拥有某个权限
        System.out.println(subject.isPermitted("employee:create"));

        //判断当前用户是否拥有列出的所有权限
        System.out.println(Arrays.toString(subject.isPermitted("employee:create", "user:delete")));

        //check的方法  和 创建角色的一样


        System.out.println(subject.hasRole("HR"));


        //执行注销操作
        subject.logout();
        System.out.println("注销后认证状态:" + subject.isAuthenticated());

    }
}
