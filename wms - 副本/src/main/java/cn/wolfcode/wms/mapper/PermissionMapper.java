package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    //分页
    Integer queryCount(QueryObject qo);
    List<Permission> queryAll(QueryObject qo);

    List<String> getListAll();

//    查询当前登陆用户拥有的权限
    List<String> selectByEmployeeId(Long id);
}