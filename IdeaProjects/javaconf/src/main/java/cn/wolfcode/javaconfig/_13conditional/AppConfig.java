package cn.wolfcode.javaconfig._13conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean
    public Car b(){
        return new Car();
    }
    @Bean
    @MyConfitional(classes = {House.class})
    public GirlFirend lily(){
        return new GirlFirend();
    }
}
