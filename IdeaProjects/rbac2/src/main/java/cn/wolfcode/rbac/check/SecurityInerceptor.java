package cn.wolfcode.rbac.check;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.util.PermissionUtil;
import cn.wolfcode.rbac.util.RequiredPermission;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

//安全拦截器
public class SecurityInerceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();
        Employee emp = (Employee) session.getAttribute("emp_in_session");
//        Employee emp = (Employee) session.getAttribute("exps_in_session");


        //超级管理员
        if (emp.isAdmin()){
            return true;
        }

        //拿到当前被访问的方法对象
        HandlerMethod hm = (HandlerMethod) handler;
        Method m = hm.getMethod();
        //判断当前方法是否需要权限

        //该方法不需要, 直接放行
        if (!m.isAnnotationPresent(RequiredPermission.class)){
            return true;
        }

        //判断当前用户是否拥有执行的权限
        String exp = PermissionUtil.builExpression(m);
        List<String> exps = (List<String>) session.getAttribute("exps_in_session");
        if(exps.contains(exp)){
            return true;
        }

        //没有权限跳转页页面
        request.getRequestDispatcher("/WEB-INF/views/common/nopermission.jsp")
                    .forward(request,response);
        return false;
    }
}
