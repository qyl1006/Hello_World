package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.mapper.EmployeeMapper;
import cn.wolfcode.rbac.mapper.PermissionMapper;
import cn.wolfcode.rbac.query.EmployeeQueryObject;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    //该成员用于查询用户所拥有的所有权限
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public void saveOrUpdate(Employee dep, Long[] roleIds) {
        if (dep.getId() != null) {
            //删除旧的关系
            employeeMapper.deleteRelation(dep.getId());
            employeeMapper.updateByPrimaryKey(dep);
        } else {
            employeeMapper.insert(dep);
        }

        //保存角色和权限的关系 employee role关系中间表
        if (roleIds != null) {
            for (Long rId : roleIds) {
                employeeMapper.insertRelation(dep.getId(), rId);
            }
        }
    }

    @Override
    public void delete(Long id) {
        //删除旧的关系
        employeeMapper.deleteRelation(id);
        employeeMapper.deleteByPrimarId(id);
    }

    @Override
    public Employee get(Long id) {
        return employeeMapper.getByPrimarId(id);
    }

    @Override
    public PageResult queryAll(EmployeeQueryObject qo) {
        Integer count = employeeMapper.queryCount(qo);
        if (count == 0){
            return PageResult.EMPTY_PAGE;
        }

        List<?> data = employeeMapper.query(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(),count, data);
    }

    //登陆业务的方法
    @Override
    public void login(String username, String password) {
        Employee emp = employeeMapper.selectEmployeeByInfo(username, password);
        if(emp == null){
            throw new RuntimeException("账号或密码不匹配");
        }

        HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                    .getRequest().getSession();

        //把当前登陆成功的用户存入session
        session.setAttribute("emp_in_session", emp);

        //把当前用户的拥有的权限表达式查询出来存入session
        List<String> exps = permissionMapper.selectExpressionsByEmployeeId(emp.getId());

        session.setAttribute("exps_in_session", exps);
    }


}
