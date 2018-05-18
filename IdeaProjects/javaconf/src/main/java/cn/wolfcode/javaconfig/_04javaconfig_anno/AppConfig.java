package cn.wolfcode.javaconfig._04javaconfig_anno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wolfcode-lanxw
 */
@Configuration
@ComponentScan
//如果不写basepackages,默认扫描当前包及其子包.
public class AppConfig {
}
