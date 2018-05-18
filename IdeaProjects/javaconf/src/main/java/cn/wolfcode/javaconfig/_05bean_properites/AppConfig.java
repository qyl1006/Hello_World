package cn.wolfcode.javaconfig._05bean_properites;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by wolfcode-lanxw
 */
@Configuration
public class AppConfig {
    //<bean id="lily" name="" class="" scope="" init-method="" destory-method="" >
    //xml配置中的id==javaconfig中的方法名
    //xml配置中的name==javaconfig中的@Bean中的name属性
    //xml配置中的scope==方法上的@Scope("prototype")注解
    @Bean(name="niuniu",initMethod = "init",destroyMethod = "destory")
    @Scope("prototype")
    public GirlFirend lily(){
        return new GirlFirend();
    }
}
