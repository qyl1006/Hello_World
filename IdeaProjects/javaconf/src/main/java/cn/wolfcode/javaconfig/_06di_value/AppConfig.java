package cn.wolfcode.javaconfig._06di_value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by wolfcode-lanxw
 */
@Configuration
public class AppConfig {
    /**
     * <bean id="" class="" scope="" init-method="" destory-method="" >
         <property name="age" value="18">
     </bean>
     */
    @Bean
    public GirlFirend lily(){
        GirlFirend lily = new GirlFirend();
        lily.setAge(18);
        return lily;
    }
}
