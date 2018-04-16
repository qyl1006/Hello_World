package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    void insert(Permission entity);
    void deleteById(Long id);

    Integer queryCount(QueryObject qo);
    List<Permission> queryAll(QueryObject qo);

    List<String> getListAll();

//    /查询所有权限, 给角色使用
    List<Permission> listAll();

//    根据员工ID查询其拥有的权限
    List<String> selectExpressionsByEmployeeId(Long id);
}
