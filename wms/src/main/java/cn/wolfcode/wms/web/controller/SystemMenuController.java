package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.query.SystemMenuQueryObject;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.util.JSONResult;
import cn.wolfcode.wms.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("systemMenu")
public class SystemMenuController {
    @Autowired
    private ISystemMenuService systemMenuService;

    @RequestMapping("list")
    public String list(@ModelAttribute("qo") SystemMenuQueryObject qo, Model model) throws Exception {
        //显示当前菜单层级
        if (qo.getParentId() != null) {
            List<SystemMenu> menus = systemMenuService.getParentMenus(qo.getParentId());
            model.addAttribute("menus", menus);
        }
        List<SystemMenu> list = systemMenuService.query(qo);
        model.addAttribute("list", list);
        return "systemMenu/list";
    }

    @RequestMapping("input")
    public String input(Long id, Long parentId, Model model) throws Exception {
        if (id != null) {
            model.addAttribute("entity", systemMenuService.get(id));
        }
        if (parentId != null) {
            model.addAttribute("parent", systemMenuService.get(parentId));
        }
        return "systemMenu/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(SystemMenu entity) throws Exception {
        systemMenuService.saveOrUpdate(entity);
        return new JSONResult();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) throws Exception {
        if (id != null) {
            systemMenuService.delete(id);
        }
        return new JSONResult();
    }

    /**
     * 根据菜单的sn获取子菜单
     [{id:2, pId:1, name: "部门管理", action: "/department/list.do"},
     {id:3, pId:1, name: "员工管理", action: "/employee/list.do"}]
     * @param menuSn
     * @return
     * @throws Exception
     */
    @RequestMapping("getMenusBySn")
    @ResponseBody
    public Object getMenusBySn(String menuSn) throws Exception {
        List<Map<String, Object>> list = systemMenuService.getMenuBySn(menuSn);
        return list;
    }
}
