package cn.wolfcode.javaconfig._08import;

import cn.wolfcode.javaconfig._08import.controller.ControllerBean;
import cn.wolfcode.javaconfig._08import.mapper.MapperBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wolfcode-lanxw
 */
@Configuration
public class MapperConfig {
    @Bean
    public MapperBean mapperBean(){
        return new MapperBean();
    }
}
