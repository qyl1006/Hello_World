package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.ParentQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    //查询所有菜单---所有有父菜单的菜单
    List<SystemMenu> getAll();


    //根据角色ID删除中间表旧的关系
    void deleteRelation(Long roleId);


    //维护关系--菜单
    void insertRelation(@Param("roleId") Long roleId,
                        @Param("menuId") Long menuId);
//    动态菜单显示功能 查所有子菜单
    List<Map<String,Object>> getMenuBySn(String menuSn);

//    动态菜单显示功能 查当前用户所拥有的子菜单
    List<Map<String,Object>> getMenuBySnAndUser(@Param("menuSn") String menuSn,
                                                @Param("employeeId") Long employeeId);
}