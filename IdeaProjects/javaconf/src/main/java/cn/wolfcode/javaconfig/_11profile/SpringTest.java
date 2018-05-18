package cn.wolfcode.javaconfig._11profile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wolfcode-lanxw
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("test")
public class SpringTest {
    @Autowired
    private MyDruidDataSource myDruidDataSource;
    @Test
    public void testBean(){
        System.out.println(myDruidDataSource);
    }
}
