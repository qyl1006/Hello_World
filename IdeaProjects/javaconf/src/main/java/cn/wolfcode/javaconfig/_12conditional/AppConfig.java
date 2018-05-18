package cn.wolfcode.javaconfig._12conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public House a(){
        return new House();
    }

    @Bean
    @Conditional(HouseCondition.class)
    public GirlFirend lily(){
        return new GirlFirend();
    }
}
