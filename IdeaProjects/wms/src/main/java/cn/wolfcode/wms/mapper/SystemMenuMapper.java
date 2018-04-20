package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.ParentQueryObject;

import java.util.List;

public interface SystemMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long id);

    List<SystemMenu> selectAll(ParentQueryObject qo);

    int updateByPrimaryKey(SystemMenu record);

    //查父菜单
    SystemMenu getParentById(Long parentId);

    //删除所有子菜单
    void deleteChildByChildtId(List<Long> childId);

    //查菜单目录层级
    SystemMenu getParentMenus(Long parentId);

    //根据父菜单查询直接子菜单
    List<SystemMenu> selectChildByparentId(Long parentId);
}