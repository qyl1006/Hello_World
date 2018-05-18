package cn.wolfcode.javaconfig._01xmlconfig;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    @Test
    public void testBean(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        GirlFirend lily = ac.getBean(GirlFirend.class);
        System.out.println(lily);
    }
}
