package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.ParentQueryObject;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.util.JSONResult;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("systemMenu")
public class SystemMenuController {
    @Autowired
    private ISystemMenuService systemMenuService;
    
    
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") ParentQueryObject qo, Model model){
        List<SystemMenu> menus = systemMenuService.listAll(qo);
        model.addAttribute("menus", menus);

        //菜单层级
        if (qo.getParentId() != null) {
            List<SystemMenu>  menuDirs = systemMenuService.getParentMenus(qo.getParentId());
            model.addAttribute("menuDirs", menuDirs);
        }

        return "systemMenu/list";
    }


    @RequestMapping("input")
    public String input(Long id, Long parentId, Model model){
        if (id != null) {
            SystemMenu entity = systemMenuService.getById(id);
            model.addAttribute("entity", entity);
        }

        if (parentId != null) {
            SystemMenu entity = systemMenuService.getParentById(parentId);
            model.addAttribute("parent", entity);
        }

        return "systemMenu/input";
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(SystemMenu entity){
        systemMenuService.insertOrUpdate(entity);

        return new JSONResult();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            systemMenuService.deleteById(id);
        }

        return  new JSONResult();
    }


    @RequestMapping("getmenusBySn")
    @ResponseBody
    public Object getmenusBySn(String menuSn){
        //取出当前登陆用户
        Employee emp = UserContext.getCurrentUser();

        //如果是超级管理员  则查询出所有菜单
        if (emp.isAdmin()) {
            return systemMenuService.getMenuBySn(menuSn);
        }
        //或者查询出当前用户拥有的菜单

        return systemMenuService.getMenuBySnAndUser(menuSn, emp.getId());
    }


}
