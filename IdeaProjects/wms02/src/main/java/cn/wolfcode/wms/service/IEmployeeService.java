package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

public interface IEmployeeService {
    void insertOrUpdate(Employee entity, Long[] ids);

    void deleteById(Long id);

    Employee getById(Long id);

    PageResult queryAll(EmployeeQueryObject qo);

    void login(String username, String password);

    void batchDelete(Long[] ids);

    Employee selectByUsername(String username);
}
