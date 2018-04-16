package cn.wolfcode.wms.web.interceptor;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//检查登陆拦截器
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //从Session中取出登陆成功的用户
        Employee emp = UserContext.getCurrentUser();
        if (emp == null) {
            response.sendRedirect("/login.html");//没有登陆,回到登陆页面
            return false;
        }
        return true;//已经登陆,放行
    }
}
