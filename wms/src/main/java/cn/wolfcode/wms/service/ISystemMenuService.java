package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.query.SystemMenuQueryObject;
import cn.wolfcode.wms.util.PageResult;

import java.util.List;
import java.util.Map;

public interface ISystemMenuService {
    void saveOrUpdate(SystemMenu entity);
    void delete(Long id);
    SystemMenu get(Long id);
    List<SystemMenu> list();

    /**
     * 菜单查询 根据父菜单ID查询
     * @param qo
     * @return
     */
    List<SystemMenu> query(SystemMenuQueryObject qo);

    /**
     * 根据菜单ID获取所有的父菜单
     * @param menuId
     * @return
     */
    List<SystemMenu> getParentMenus(Long menuId);

    /**
     * 根据父菜单的SN查询子菜单
     * @param menuSn
     * @return
     */
    List<Map<String, Object>> getMenuBySn(String menuSn);
}
