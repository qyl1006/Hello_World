package cn.wolfcode.javaconfig._02xmlconfig_anno;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wolfcode-lanxw
 */
public class SpringTest {
    @Test
    public void testBean(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext_anno.xml");
        GirlFirend lily = ac.getBean(GirlFirend.class);
        System.out.println(lily);
    }
}
