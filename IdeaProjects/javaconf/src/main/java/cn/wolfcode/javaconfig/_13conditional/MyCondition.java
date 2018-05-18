package cn.wolfcode.javaconfig._13conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;


public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //获取到注解上的属性的   map集合
        Map<String, Object> attributes = metadata.getAnnotationAttributes(MyConfitional.class.getName());
        //获取classes对应的值
        Class[] classes = (Class[]) attributes.get("classes");
        //遍历集合,判断容器中是否有对于类型的bean
        for(Class clazz :classes){
            if(context.getBeanFactory().getBeansOfType(clazz).size()<=0){
                return false;
            }
        }
        return true;
    }
}
