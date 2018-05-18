package cn.wolfcode.javaconfig._08import;

import cn.wolfcode.javaconfig._08import.controller.ControllerBean;
import cn.wolfcode.javaconfig._08import.mapper.MapperBean;
import cn.wolfcode.javaconfig._08import.service.ServiceBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wolfcode-lanxw
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class SpringTest {
    @Autowired
    private ControllerBean controllerBean;
    @Autowired
    private ServiceBean serviceBean;
    @Autowired
    private MapperBean mapperBean;
    @Test
    public void testBean(){
        System.out.println(controllerBean);
        System.out.println(serviceBean);
        System.out.println(mapperBean);
    }
}
