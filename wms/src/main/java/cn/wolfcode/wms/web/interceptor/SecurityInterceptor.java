package cn.wolfcode.wms.web.interceptor;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.util.PermissionUtil;
import cn.wolfcode.wms.util.RequiredPermission;
import cn.wolfcode.wms.util.MySecurityException;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

//权限检查拦截器
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //取出当前登陆的用户
        Employee emp = UserContext.getCurrentUser();
        //超级管理员,放行
        if (emp.isAdmin()) {
            return true;
        }
        //当前访问的方法是否需要权限
        HandlerMethod hm = (HandlerMethod) handler;
        Method m = hm.getMethod();
        if (!m.isAnnotationPresent(RequiredPermission.class)) {
            //当前方法不要权限,放行
            return true;
        }

        //当前方法需要权限,当前用户是否拥有该权限
        List<String> exps = UserContext.getExpression();
        String exp = PermissionUtil.buildExpression(m);
        if (exps.contains(exp)) {
            //当前用户有权限,放行
            return true;
        }

        throw new MySecurityException();
    }
}
