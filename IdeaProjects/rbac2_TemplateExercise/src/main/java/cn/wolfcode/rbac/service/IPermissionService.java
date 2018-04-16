package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;

import java.util.List;

public interface IPermissionService {
    void deleteById(Long id);

    PageResult queryAll(QueryObject qo);

    void reload();


    //查询所有权限, 给角色使用
    List<Permission> listAll();

//    根据员工ID查询其拥有的权限
    List<String> selectExpressionsByEmployeeId(Long id);
}
