package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    //分页
    Integer queryCount(QueryObject qo);
    List<Role> queryAll(QueryObject qo);

    void deleteRelation(Long id);

    void insertRelation(@Param("roleId") Long roleId,
                        @Param("permissionId") Long permissionId);
}