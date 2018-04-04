package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;

import java.util.List;

public interface IPermissionService {

    /**
     * 删除权限
     * @param id ID
     */
    void deleteById(Long id);

    /**
     * 分页查询
     * @param qo 分页对象
     * @return
     */
    PageResult queryAll(QueryObject qo);


    /**加载权限----service实现类具体实现---
    * 最后把获取到的domain对象(Permission)通过保存方法保存数据库
     *
     */
    void reload();

    /**
     * 查询所有权限信息
     * @return 返回所有权限的list集合
     */
    List<Permission> listAll();
}
