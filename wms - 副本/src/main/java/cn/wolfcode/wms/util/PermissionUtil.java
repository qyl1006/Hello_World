package cn.wolfcode.wms.util;

import java.lang.reflect.Method;

public abstract class PermissionUtil {
    // 构建出权限表达式  自定义的
    public static String builExpression(Method m){
        String className = m.getDeclaringClass().getName();
        return className + ":" + m.getName();
    }
}
