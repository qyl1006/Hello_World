package cn.wolfcode.javaconfig._11profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by wolfcode-lanxw
 */
@Configuration
@PropertySource("classpath:jdbc_dev.properties")
@Profile("dev")
public class DevConfig {

}
