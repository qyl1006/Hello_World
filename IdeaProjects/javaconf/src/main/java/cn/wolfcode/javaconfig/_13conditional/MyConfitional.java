package cn.wolfcode.javaconfig._13conditional;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Conditional(MyCondition.class)
public @interface MyConfitional {
    Class[] classes() default {};
}
