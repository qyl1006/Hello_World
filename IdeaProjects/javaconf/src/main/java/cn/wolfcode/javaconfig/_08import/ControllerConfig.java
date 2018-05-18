package cn.wolfcode.javaconfig._08import;

import cn.wolfcode.javaconfig._08import.controller.ControllerBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wolfcode-lanxw
 */
@Configuration
public class ControllerConfig {
    @Bean
    public ControllerBean controllerBean(){
        System.out.println("in.......");
        return new ControllerBean();
    }
}
