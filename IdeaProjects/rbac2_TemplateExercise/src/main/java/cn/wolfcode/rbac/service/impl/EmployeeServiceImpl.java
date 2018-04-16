package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.mapper.EmployeeMapper;
import cn.wolfcode.rbac.query.EmployeeQueryObject;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.service.IEmployeeService;
import cn.wolfcode.rbac.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    //查询权限表达式
    @Autowired
    private IPermissionService permissionService;


    @Override
    public void insertOrUpdate(Employee entity, Long[] roleIds) {
        if (entity.getId() == null) {
            employeeMapper.insert(entity);
        }else {
            //更新前, 删除旧的关系  中间表的
            employeeMapper.deleteRelation(entity.getId());

            employeeMapper.updateByKey(entity);
        }

        //最后发额外的sql语句  维护外键关系
        if (roleIds != null) {
            for (Long role : roleIds) {
                employeeMapper.insertRelation(entity.getId(), role);
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        //更新前, 删除旧的关系  中间表的
        employeeMapper.deleteRelation(id);

        employeeMapper.deleteById(id);
    }

    @Override
    public Employee getById(Long id) {
        return employeeMapper.getById(id);
    }

    @Override
    public PageResult queryAll(EmployeeQueryObject qo) {
        Integer count = employeeMapper.queryCount(qo);
        if(count == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Employee> data = employeeMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), count,data);
    }

    //检查登陆的具体操作
    @Override
    public void login(String username, String password) {
        //查数据库验证
        Employee entity = employeeMapper.selectEmployeeByInfo(username, password);
        if(entity == null){
            throw new RuntimeException("账号或密码不匹配");
        }

        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest().getSession();

        //把当前登陆成功的用户存入session
        session.setAttribute("emp_in_session", entity);

        //把当前用户拥有的权限表达式查询出来存入session
        List<String> exps = permissionService.selectExpressionsByEmployeeId(entity.getId());
        session.setAttribute("exps_in_session", exps);
    }
}
