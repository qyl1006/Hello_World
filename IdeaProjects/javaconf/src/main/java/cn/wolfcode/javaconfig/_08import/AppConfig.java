package cn.wolfcode.javaconfig._08import;

import cn.wolfcode.javaconfig._08import.controller.ControllerBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by wolfcode-lanxw
 */
@Configuration
@Import({
        ControllerConfig.class,
        ServiceConfig.class,
        MapperConfig.class
})
public class AppConfig {
}
