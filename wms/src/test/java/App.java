import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.service.IDepartmentService;
import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class App {
    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void testDruid() throws Exception {
        //lCzd9geWAuAuJtLhpaG/J+d28H8KiMFAWopxXkYpMNdUai6Xe/LsPqMQeg5MIrmvtMa+hzycdRhWs29ZUPU1IQ==
        System.out.println(ConfigTools.encrypt("admin"));
        System.out.println(ConfigTools.decrypt("lCzd9geWAuAuJtLhpaG/J+d28H8KiMFAWopxXkYpMNdUai6Xe/LsPqMQeg5MIrmvtMa+hzycdRhWs29ZUPU1IQ=="));
    }

    @Test
    public void saveOrUpdate() {
        System.out.println(departmentService.getClass());
        Department d = new Department();
        d.setId(1L);
        d.setName("总经办");
        d.setSn("BOSS");
        departmentService.saveOrUpdate(d);
    }

    @Test
    public void delete() {
        departmentService.delete(2L);
    }

    @Test
    public void get() {
        System.out.println(departmentService.get(1L));
    }

    @Test
    public void list() {
        departmentService.list().forEach(System.out::println);
    }
}