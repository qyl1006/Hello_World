package cn.wolfcode.rbac.util;

import java.lang.reflect.Method;

abstract public class PermissionUtil {
    //根据方法, 构建出权限表达式(类全限定名:方法名称)
    public static String builExpression(Method m){
        String className = m.getDeclaringClass().getName();
        return className + ":" + m.getName();
    }
}
