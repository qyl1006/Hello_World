package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.query.EmployeeQueryObject;
import cn.wolfcode.rbac.query.PageResult;

public interface IEmployeeService {
    /**
     * 保存或更新
     * @param dep 员工对象
     * @param roleIds 角色ID的Long数组
     */
    void saveOrUpdate(Employee dep, Long[] roleIds);

    /**
     * 删除
     * @param id ID
     */
    void delete(Long id);

    /**
     * 查询一个员工信息
     * @param id 员工ID
     * @return 返回员工对象,若没有返回Null
     */
    Employee get(Long id);

    //多条件查询

    /**
     * 多条件下高级查询
     * @param qo 多条件封装的对象
     * @return 返回页面PageResult对象
     */
    PageResult queryAll(EmployeeQueryObject qo);


    /**
     * 登陆的业务方法
     * @param username 账号
     * @param password 密码
     */
    void login(String username, String password);
}
