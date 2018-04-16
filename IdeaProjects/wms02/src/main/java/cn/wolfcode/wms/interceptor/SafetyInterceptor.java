package cn.wolfcode.wms.interceptor;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.util.MySecurityException;
import cn.wolfcode.wms.util.PermissionUtil;
import cn.wolfcode.wms.util.RequirePermission;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

public class SafetyInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Employee emp = UserContext.getCurrentUser();

        if(emp.isAdmin()){
            return true;
        }

        //拿到当前被访问的方法对象
        HandlerMethod hm = (HandlerMethod) handler;
        Method m = hm.getMethod();
        //判断当前方法是否需要权限

        //该方法不需要, 直接放行
        if (!m.isAnnotationPresent(RequirePermission.class)){
            return true;
        }

        List<String> exps = UserContext.getExpression();
        String exp = PermissionUtil.builExpression(m);
        if(exps.contains(exp)){
            return true;
        }

        //可以定义异常类

        throw new MySecurityException("没有相关权限访问操作---测试自定义异常");
//        request.getRequestDispatcher("/WEB-INF/views/common/nopermission.jsp").forward(request, response);
//        return false;
    }
}
