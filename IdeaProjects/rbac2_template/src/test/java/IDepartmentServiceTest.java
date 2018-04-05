import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.service.IDepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class IDepartmentServiceTest {
    @Autowired
    private IDepartmentService departmentService;
//
//    @Test
//    public void insertOrUpdate() {
//        departmentService.insertOrUpdate(new Department(4L, "业务部", "YY"));
//    }
//
//    @Test
//    public void deleteById() {
//        departmentService.deleteById(4L);
//    }
//
//    @Test
//    public void getById() {
//        System.out.println(departmentService.getById(2L));
//    }
}
