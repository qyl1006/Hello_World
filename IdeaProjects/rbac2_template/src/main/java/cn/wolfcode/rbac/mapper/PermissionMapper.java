package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    //增加权限
    void insert(Permission entuty);
    //删除权限
    void deleteById(Long id);


    //记录数--分页用
    Integer queryCount(QueryObject qo);
    //所有数据--分页条件下
    List<?>  queryAll(QueryObject qo);

    //查询数据库中所有的expression---权限的全限定名表达式
    List<String> selectAllExpressions();

    List<Permission> listAll();

    //把当前用户的拥有的权限表达式查询出来
    List<String> selectExpressionsByEmployeeId(Long id);


}
