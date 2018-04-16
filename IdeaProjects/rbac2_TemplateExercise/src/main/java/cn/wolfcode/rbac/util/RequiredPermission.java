package cn.wolfcode.rbac.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//加载到JVM
@Target(ElementType.METHOD) //贴方法上
public @interface RequiredPermission {
    String value();  //权限名称
}
