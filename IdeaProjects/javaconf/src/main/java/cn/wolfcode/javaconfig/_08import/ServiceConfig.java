package cn.wolfcode.javaconfig._08import;

import cn.wolfcode.javaconfig._08import.mapper.MapperBean;
import cn.wolfcode.javaconfig._08import.service.ServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wolfcode-lanxw
 */
@Configuration
public class ServiceConfig {
    @Bean
    public ServiceBean serviceBean(){
        return new ServiceBean();
    }
}
