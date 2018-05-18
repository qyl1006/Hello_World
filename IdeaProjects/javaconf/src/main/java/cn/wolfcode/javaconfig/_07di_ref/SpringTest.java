package cn.wolfcode.javaconfig._07di_ref;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by wolfcode-lanxw
 */
public class SpringTest {
    @Test
    public void testBean(){
        try(AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class)){
            GirlFirend lily = ac.getBean(GirlFirend.class);
            System.out.println(lily);
        }
    }
}
