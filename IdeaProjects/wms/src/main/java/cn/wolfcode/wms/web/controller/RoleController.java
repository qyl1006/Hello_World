package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;
    //菜单
    @Autowired
    private ISystemMenuService systemMenuService;
    
    @RequestMapping("list")
    public String list(QueryObject qo, Model model){
        PageResult result = roleService.queryAll(qo);
        model.addAttribute("result", result);

        return "role/list";
    }


    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null) {
            Role entity = roleService.getById(id);
            model.addAttribute("entity", entity);
        }
        //权限
        model.addAttribute("permissions", permissionService.listAll());
        //菜单
        model.addAttribute("menus", systemMenuService.selectAll());
        return "role/input";
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Role entity, Long[] ids, Long[] menuIds){
        roleService.insertOrUpdate(entity, ids, menuIds);

        return new JSONResult();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            roleService.deleteById(id);
        }

        return  new JSONResult();
    }


    @RequestMapping("test")
    @ResponseBody
    public Object test(String username){
        System.out.println(username);
        return new JSONResult();
    }


}
