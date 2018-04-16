package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import cn.wolfcode.wms.query.PageResult;

public interface IEmployeeService {
    void insertOrUpdate(Employee entity, Long[] ids);

    void deleteById(Long id);

    Employee getById(Long id);



    //高级/分页查询
    PageResult queryAll(EmployeeQueryObject qo);

    void login(String username, String password);
}
