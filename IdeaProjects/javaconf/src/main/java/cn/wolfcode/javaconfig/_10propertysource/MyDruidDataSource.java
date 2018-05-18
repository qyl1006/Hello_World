package cn.wolfcode.javaconfig._10propertysource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sun.security.util.Password;

/**
 * Created by wolfcode-lanxw
 */
@Setter@Getter@ToString
public class MyDruidDataSource {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
