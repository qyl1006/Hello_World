package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ParentQueryObject;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface ISystemMenuService {
    void insertOrUpdate(SystemMenu entity);

    void deleteById(Long id);

    SystemMenu getById(Long id);

    List<SystemMenu> listAll(ParentQueryObject qo);


    SystemMenu getParentById(Long parentId);

    List<SystemMenu> getParentMenus(Long parentId);

    List<SystemMenu> selectAll();

    //动态菜单显示功能 查所有子菜单admin
    List<Map<String, Object>> getMenuBySn(String menuSn);

    //动态菜单显示功能 查当前用户所拥有的子菜单
    List<Map<String, Object>> getMenuBySnAndUser(String menuSn, Long employeeId);
}
