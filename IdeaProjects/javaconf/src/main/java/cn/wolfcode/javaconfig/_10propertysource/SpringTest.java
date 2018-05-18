package cn.wolfcode.javaconfig._10propertysource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wolfcode-lanxw
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class SpringTest {
    @Autowired
    private MyDruidDataSource myDruidDataSource;
    @Test
    public void testBean(){
        System.out.println(myDruidDataSource);
    }
}
