package cn.wolfcode.javaconfig._03javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wolfcode-lanxw
 */
@Configuration
public class AppConfig {
    @Bean
    public GirlFirend lily(){
        return new GirlFirend();
    }
}
