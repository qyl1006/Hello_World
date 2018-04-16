package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;
import cn.wolfcode.rbac.service.IPermissionService;
import cn.wolfcode.rbac.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @Autowired
    //查询权限使用
    private IPermissionService permissionService;

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
            model.addAttribute("role", entity);
        }

        model.addAttribute("permissions", permissionService.listAll());
        return "role/input";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Role entity, Long[] permissionIds){
        roleService.insertOrUpdate(entity, permissionIds);
        return "redirect:/role/list.do";
    }

    @RequestMapping("delete")
    public String delete(Long id){
        roleService.deleteById(id);
        return "redirect:/role/list.do";
    }
}
