package cn.wolfcode.javaconfig._09importresources;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by wolfcode-lanxw
 */
@Configuration
@ImportResource("classpath:applicationContext_mix.xml")
public class AppConfig {
}
