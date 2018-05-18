package cn.wolfcode.javaconfig._07di_ref;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by wolfcode-lanxw
 */
@Configuration
public class AppConfig {
    /**
     *
     <bean id="nanny" class="Nanny"></bean>
    <bean id="" class="GirlFirend" >
         <property name="nanny" ref="nanny">
     </bean>
     */
    @Bean
    public Nanny rongmomo(){
        return new Nanny("容嬷嬷");
    }
    @Bean
    public Nanny limomo(){
        return new Nanny("李嬷嬷");
    }
    @Bean
    public GirlFirend lily(@Qualifier("limomo") Nanny nanny){
        System.out.println("创建女朋友方法");
        GirlFirend lily = new GirlFirend();
        lily.setNanny(nanny);
        return lily;
    }
}
