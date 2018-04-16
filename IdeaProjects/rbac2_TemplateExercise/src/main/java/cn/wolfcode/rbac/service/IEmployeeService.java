package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.EmployeeQueryObject;

public interface IEmployeeService {
    void insertOrUpdate(Employee entity, Long[] roleIds);
    void deleteById(Long id);
    Employee getById(Long id);

    PageResult queryAll(EmployeeQueryObject qo);

    void login(String username, String password);
}
