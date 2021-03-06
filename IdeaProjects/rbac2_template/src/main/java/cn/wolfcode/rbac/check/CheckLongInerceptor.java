package cn.wolfcode.rbac.check;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLongInerceptor extends HandlerInterceptorAdapter {

    //返回 true放行 false不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Object emp = request.getSession().getAttribute("emp_in_session");
        if (emp == null){
            response.sendRedirect("/login.jsp");
            return false;
        }
        return true;
    }
}
