package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    void insert(Role entity);
    void updateByKey(Role entity);
    void deleteById(Long id);
    Role getById(Long id);

    Integer queryCount(QueryObject qo);
    List<Role> queryAll(QueryObject qo);

    List<Role> listAll();

    //删除旧的关系  删除中间表的数据
    void deleteRelation(Long id);

    //保存角色和权限的关系
    void insertRelation(@Param("roleId") Long roleId,
                        @Param("permissionId") Long permissionId);
}
