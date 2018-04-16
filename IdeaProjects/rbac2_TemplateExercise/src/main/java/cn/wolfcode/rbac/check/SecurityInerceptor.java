package cn.wolfcode.rbac.check;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.util.PermissionUtil;
import cn.wolfcode.rbac.util.RequiredPermission;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

//权限检查
public class SecurityInerceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Employee emp = (Employee) request.getSession().getAttribute("emp_in_session");

        if(emp.isAdmin()){
            return true;
        }

        //拿到当前被访问的方法对象
        HandlerMethod hm = (HandlerMethod) handler;
        Method m = hm.getMethod();

        //判断当前方法是否需要权限
        if(!m.isAnnotationPresent(RequiredPermission.class)){
            return true;
        }

        //得到访问方法的权限表达式
        String exp = PermissionUtil.builExpression(m);
        //获取当前用户所拥有的权限表达式
        List<String> exps = (List<String>) request.getSession().getAttribute("exps_in_session");
        if(exps.contains(exp)){
            return true;
        }


        //否则没相关操作权限  跳转提示页面
        request.getRequestDispatcher("/WEB-INF/views/common/nopermission.jsp")
                .forward(request, response);
        return false;

    }
}
