package cn.wolfcode.wms.interceptor;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLoginInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Employee emp = UserContext.getCurrentUser();
        if (emp == null) {
            response.sendRedirect("/login.jsp");
            return false;
        }

        return true;
    }
}
