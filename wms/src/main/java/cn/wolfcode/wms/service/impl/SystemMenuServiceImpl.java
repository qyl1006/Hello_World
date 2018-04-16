package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.mapper.SystemMenuMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.query.SystemMenuQueryObject;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.util.PageResult;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class SystemMenuServiceImpl implements ISystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;

    public List<Map<String, Object>> getMenuBySn(String menuSn) {
        Employee emp = UserContext.getCurrentUser();
        if (emp.isAdmin()) { //超级管理查看所有菜单
            return systemMenuMapper.selectMenuBySn(menuSn);
        }
        //根据父菜单的SN和当前用户查询拥有的菜单
        return systemMenuMapper.selectMenuBySnAndUserId(menuSn, emp.getId());
    }

    public List<SystemMenu> getParentMenus(Long menuId) {
        //定义集合存储菜单
        List<SystemMenu> menus = new ArrayList<>();
        SystemMenu menu = systemMenuMapper.selectByPrimaryKey(menuId);
        while (menu != null) {
            menus.add(menu);
            //父菜单不为null,继续获取当前菜单的父菜单
            //获取当前菜单的父菜单
            menu = menu.getParent();
        }
        //逆序
        Collections.reverse(menus);
        return menus;
    }

    public List<SystemMenu> query(SystemMenuQueryObject qo) {
        return systemMenuMapper.queryForMenu(qo);
    }

    public void saveOrUpdate(SystemMenu entity) {
        if (entity.getId() == null) {
            systemMenuMapper.insert(entity);
        } else {
            systemMenuMapper.updateByPrimaryKey(entity);
        }
    }

    public void delete(Long id) {
        //先删除子菜单
        systemMenuMapper.deleteChildMenu(id);
        //再删除自己
        systemMenuMapper.deleteByPrimaryKey(id);
    }

    public SystemMenu get(Long id) {
        return systemMenuMapper.selectByPrimaryKey(id);
    }

    public List<SystemMenu> list() {
        return systemMenuMapper.selectAll();
    }
}
