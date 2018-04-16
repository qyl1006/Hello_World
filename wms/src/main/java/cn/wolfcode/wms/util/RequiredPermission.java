package cn.wolfcode.wms.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //能加载到JVM中
@Target(ElementType.METHOD) //可以贴在方法上
public @interface RequiredPermission {
    String value();
}
