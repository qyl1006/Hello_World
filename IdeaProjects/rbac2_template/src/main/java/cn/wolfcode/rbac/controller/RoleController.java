package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.query.QueryObject;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.service.IDepartmentService;
import cn.wolfcode.rbac.service.IPermissionService;
import cn.wolfcode.rbac.service.IRoleService;
import cn.wolfcode.rbac.util.RequiredPermission;
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
    private IPermissionService permissionService;

   

    @RequestMapping("list")
    public String list(QueryObject qo, Model model){
        PageResult results = roleService.queryAll(qo);
        model.addAttribute("result", results);

        return "role/list";
    }

    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null){
            Role role = roleService.getByIId(id);
            model.addAttribute("role", role);
        }
        model.addAttribute("permissions", permissionService.listAll());
        return "role/input";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Role entity, Long[] permissionIds){
        roleService.saveOrUpdate(entity, permissionIds);
        return "redirect:/role/list.do";
    }

    @RequiredPermission("删除员工")
    @RequestMapping("delete")
    public String delete(Long id){
        if (id != null){
            roleService.deleteById(id);
        }
        return "redirect:/role/list.do";
    }
}
