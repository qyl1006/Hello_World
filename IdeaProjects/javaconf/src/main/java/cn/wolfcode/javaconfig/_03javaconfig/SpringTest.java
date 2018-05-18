package cn.wolfcode.javaconfig._03javaconfig;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by wolfcode-lanxw
 */
public class SpringTest {
    @Test
    public void testBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        GirlFirend lily = ac.getBean(GirlFirend.class);
        System.out.println(lily);
    }
}
