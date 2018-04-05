package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    //增加角色  11
    void insert(Role entuty);
    //删除角色  11
    void deleteById(Long id);


    //记录数--分页用  11
    Integer queryCount(QueryObject qo);
    //所有数据--分页条件下  11
    List<?>  queryAll(QueryObject qo);

    //单个查询   111
    Role getByIId(Long id);

    //删除之前的旧的关系  11
    void deleteRelation(Long id);

    //更改角色  111
    void updateByPrimaryKey(Role entity);

    //维护关系      11
    void insertRelation(@Param("roleId") Long roleId,
                        @Param("permissionId")  Long permissionId);

    //查询所有角色---给员工类使用--新增编辑功能显示选择
    List<Role> listAll();
}
