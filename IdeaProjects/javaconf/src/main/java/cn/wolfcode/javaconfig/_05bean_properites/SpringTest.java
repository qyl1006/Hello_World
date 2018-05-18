package cn.wolfcode.javaconfig._05bean_properites;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by wolfcode-lanxw
 */
public class SpringTest {
    @Test
    public void testBean(){
        try(AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class)){
            GirlFirend lily = (GirlFirend) ac.getBean("niuniu");
            System.out.println(lily);
            GirlFirend lily2 = (GirlFirend) ac.getBean("niuniu");
            System.out.println(lily2);
        }
    }
}
