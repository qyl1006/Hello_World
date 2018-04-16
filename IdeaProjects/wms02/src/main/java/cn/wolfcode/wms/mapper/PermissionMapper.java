package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);


    List<Permission> selectAll();

    //分页
    int queryCount(QueryObject qo);
    List<Permission> queryAll(QueryObject qo);

    List<String> getListAll();

    List<String> selectExpressionByEmployeeId(Long id);
}