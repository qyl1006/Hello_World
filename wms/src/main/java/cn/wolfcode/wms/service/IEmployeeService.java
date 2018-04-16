package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.util.PageResult;

import java.util.List;

public interface IEmployeeService {
    void saveOrUpdate(Employee entity, Long[] ids);
    void delete(Long id);
    Employee get(Long id);

    PageResult query(QueryObject qo);

    /**
     * 登陆业务方法
     * @param username
     * @param password 记得加密
     */
    void login(String username, String password);

    /**
     * 批量删除
     * @param ids 批量删除的对象的id
     */
    void batchDelete(List<Long> ids);
}
