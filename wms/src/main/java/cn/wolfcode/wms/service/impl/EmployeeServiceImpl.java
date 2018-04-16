package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.mapper.EmployeeMapper;
import cn.wolfcode.wms.mapper.PermissionMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.util.MD5;
import cn.wolfcode.wms.util.PageResult;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    public void batchDelete(List<Long> ids) {
        employeeMapper.batchDelete(ids);
    }

    public void login(String username, String password) {
        //密码记得加密
        Employee emp = employeeMapper.selectByInfo(username, MD5.encoder(password));
        if (emp == null) {
            throw new RuntimeException("账号和密码不匹配");
        }
        //把登陆成功的用户存入session
        UserContext.setCurrentUser(emp);
        //如果非超级管理员,把当前用户的权限存入session
        if (!emp.isAdmin()) {
            List<String> exps = permissionMapper.selectByEmployeeId(emp.getId());
            UserContext.setExpression(exps);
        }
    }

    public void saveOrUpdate(Employee entity, Long[] ids) {
        if (entity.getId() == null) {
            //保存员工之前,对员工密码加密
            String newPwd = MD5.encoder(entity.getPassword());
            entity.setPassword(newPwd);//设置用户密文密码
            employeeMapper.insert(entity);
        } else {
            employeeMapper.deleteRelation(entity.getId());
            employeeMapper.updateByPrimaryKey(entity);
        }

        if (ids != null) {
            for (Long roleId : ids) {
                employeeMapper.insertRelation(entity.getId(), roleId);
            }
        }
    }

    public void delete(Long id) {
        employeeMapper.deleteRelation(id);
        employeeMapper.deleteByPrimaryKey(id);
    }

    public Employee get(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    public PageResult query(QueryObject qo) {
        //总记录数
        Integer rows = employeeMapper.queryForCount(qo);
        if (rows == 0) {
            return PageResult.EMPTY_PAGE;
        }
        //----------------------------
        List<?> data = employeeMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
    }
}
